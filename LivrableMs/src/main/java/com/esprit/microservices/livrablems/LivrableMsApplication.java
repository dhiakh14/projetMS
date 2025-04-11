package com.esprit.microservices.livrablems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LivrableMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrableMsApplication.class, args);
	}

}
