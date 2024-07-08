package com.example.bughunteraz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.bughunteraz.service.email"})
public class BughunterazApplication {

    public static void main(String[] args) {
        SpringApplication.run(BughunterazApplication.class, args);
    }

}
