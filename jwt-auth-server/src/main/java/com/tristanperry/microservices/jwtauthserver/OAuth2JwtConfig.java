package com.tristanperry.microservices.jwtauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.Assert;

@Configuration
@EnableAuthorizationServer
public class OAuth2JwtConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${application.jwtSigningKey}")
    private String jwtSigningKey;

    @Autowired
    private AuthenticationManager authManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        Assert.notNull(jwtSigningKey, "No JWT signing key present, check config params for application.jwtSigningKey");

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtSigningKey);
        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("default-client")
                .secret("sssshhh")
                .authorizedGrantTypes("password")
                .authorities("ROLE_ADMIN", "USER")
                .scopes("read", "write", "report")
                .resourceIds("default-resources")
                .accessTokenValiditySeconds(99999);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(this.accessTokenConverter())
                .authenticationManager(authManager);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}
