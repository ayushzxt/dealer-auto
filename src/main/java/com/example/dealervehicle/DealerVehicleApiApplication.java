package com.example.dealervehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DealerVehicleApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealerVehicleApiApplication.class, args);
    }
}




