package com.example.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.example.gradledemo.controller", "com.example.gradledemo.service"})
public class GradledemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GradledemoApplication.class, args);
    }
}
