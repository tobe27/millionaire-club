package com.millionaire.millionairequartzmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.millionaire"})
public class MillionaireQuartzManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireQuartzManagerApplication.class, args);
	}
}
