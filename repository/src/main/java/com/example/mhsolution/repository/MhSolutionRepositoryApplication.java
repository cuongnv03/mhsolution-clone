package com.example.mhsolution.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.mhsolution")
public class MhSolutionRepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MhSolutionRepositoryApplication.class, args);
    }
}