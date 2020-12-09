package com.sergio.bookstore.service.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PricesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesServiceApplication.class, args);
	}

}
