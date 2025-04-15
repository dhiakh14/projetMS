package com.example.sprinproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
<<<<<<< HEAD
import org.springframework.cloud.openfeign.EnableFeignClients;
=======
>>>>>>> origin/lahmer
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
<<<<<<< HEAD
@EnableFeignClients
=======
>>>>>>> origin/lahmer
public class PlanningApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanningApplication.class, args);
    }

}
