package com.tristanperry.microservices.businesslogicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages="com.tristanperry.microservices")
public class BusinessLogicApiApp {

    public static void main(String[] args) {
        SpringApplication.run(BusinessLogicApiApp.class, args);
    }
}
