package com.monirul.test.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class EmailserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailserviceApplication.class, args);
    }

}
