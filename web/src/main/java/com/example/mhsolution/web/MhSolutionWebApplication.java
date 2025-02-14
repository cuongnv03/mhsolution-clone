package com.example.mhsolution.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.mhsolution")
public class MhSolutionWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MhSolutionWebApplication.class, args);
    }
}