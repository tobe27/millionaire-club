package com.millionaire.millionairepaymentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.millionaire"})
public class MillionairePaymentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionairePaymentManagerApplication.class, args);
	}
}
