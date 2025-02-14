package com.example.mhsolution.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.mhsolution")
public class MhSolutionDomainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MhSolutionDomainApplication.class, args);
    }
}