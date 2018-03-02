package com.tristanperry.microservices.reportsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages="com.tristanperry.microservices")
public class ReportsApiApp {

    public static void main(String[] args) {
        SpringApplication.run(ReportsApiApp.class, args);
    }
}
