package com.example.demo;

import com.example.demo.model.ClassContainer;
import com.example.demo.util.HibernateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        //ClassContainer classContainer = new ClassContainer();
        //classContainer.addClass("Grupa1", 25);
        SpringApplication.run(DemoApplication.class, args);
    }

}
