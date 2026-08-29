package com.michelle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        PlantDatabase.createTable();
        SpringApplication.run(Main.class, args);
    }
}