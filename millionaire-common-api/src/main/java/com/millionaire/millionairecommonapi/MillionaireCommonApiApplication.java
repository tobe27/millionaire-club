package com.millionaire.millionairecommonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.millionaire.millionairecommonapi")
public class MillionaireCommonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireCommonApiApplication.class, args);
	}
}
