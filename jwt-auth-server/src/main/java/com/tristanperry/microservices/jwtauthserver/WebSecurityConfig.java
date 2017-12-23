package com.tristanperry.microservices.jwtauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Create a quick in memory list of two users (with different roles) who can login to the system:
     *   - user - password
     *   - admin - password
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // creating user in memory
                .withUser("user")
                .password("password").roles("USER")
                .and().withUser("admin")
                .password("password").authorities("ROLE_ADMIN");
    }

    @Override
    // TODO This is no longer needed AFAIK
    // protected void configure(HttpSecurity http) throws Exception {
    //     http
    //             .authorizeRequests()
    //             .antMatchers("/validate/jwt", "/user", "/check/user").permitAll()
    //             .anyRequest().authenticated()
    //             .and()
    //             .logout()
    //             .permitAll();
    // }

}
