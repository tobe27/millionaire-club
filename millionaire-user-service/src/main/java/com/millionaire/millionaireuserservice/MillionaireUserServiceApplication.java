package com.millionaire.millionaireuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.millionaire"})

public class MillionaireUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireUserServiceApplication.class, args);
	}
}
