package com.example.mhsolution.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.mhsolution")
public class MhSolutionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MhSolutionServiceApplication.class, args);
    }
}