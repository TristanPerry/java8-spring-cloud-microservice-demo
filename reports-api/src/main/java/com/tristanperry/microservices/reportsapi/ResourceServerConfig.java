package com.tristanperry.microservices.reportsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
// TODO Duplicate of the one in business logic API
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.jwt.keyValue}")
    private String signingKey;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/public/**").permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.resourceId("default-resources"); // this matches the resourceId in OAuth2JwtConfig
        config.tokenServices(this.getTokenService());
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(this.jwtTokenConverter());
    }

    @Bean
    // Get this resource server to verify its own JWT token, instead of passing the request to the jwt-server via security.oauth2.resource.userInfoUri
    public JwtAccessTokenConverter jwtTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices getTokenService() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.jwtTokenStore());
        return defaultTokenServices;
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    // 'Activate' the @PreAuthorize annotations we use to only allow access to controller methods based on roles/scopes (etc) in the oauth2 token
    public static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
        @Override
        protected MethodSecurityExpressionHandler createExpressionHandler() {
            return new OAuth2MethodSecurityExpressionHandler();
        }
    }

}
