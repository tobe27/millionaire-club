package com.millionaire.millionaireclientweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.millionaire"})
public class MillionaireClientWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireClientWebApplication.class, args);
	}
}
