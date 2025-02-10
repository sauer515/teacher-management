package com.example.demo;

import com.example.demo.model.ClassContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ClassContainer classContainer = new ClassContainer();
        classContainer.addClass("Grupa1", 25);
        SpringApplication.run(DemoApplication.class, args);
    }

}
