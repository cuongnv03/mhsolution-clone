package com.example.mhsolution.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.mhsolution")
public class MhSolutionCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(MhSolutionCommonApplication.class, args);
    }
}