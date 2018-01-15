package com.tristanperry.microservices.jwtauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DataController {

    @GetMapping("/public/data")
    public List<String> getPublicData() {
        return Arrays.asList(new String[] {"Some", "public", "data", "here"});
    }

    @GetMapping("/non-public/data")
    public List<String> getNonPublicData() {
        return Arrays.asList(new String[] {"Some", "non-public", "data", "here"});
    }

}
