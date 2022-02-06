package com.sergio.bookstore.service.mails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailsServiceApplication.class, args);
	}

}
