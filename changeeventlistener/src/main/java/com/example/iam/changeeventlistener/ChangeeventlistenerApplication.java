package com.example.iam.changeeventlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ChangeeventlistenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChangeeventlistenerApplication.class, args);
	}

	@Bean
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
