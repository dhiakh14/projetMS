package com.pi.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apigatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route("Candidat",r->r.path("/candidats/**")
						.uri("lb://candidat") )
				.route("Job", r->r.path("/jobs/**")
						.uri("lb://job") )
				.build();
	}
}



