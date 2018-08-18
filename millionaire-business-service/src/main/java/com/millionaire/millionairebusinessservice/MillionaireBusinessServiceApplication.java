package com.millionaire.millionairebusinessservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MillionaireBusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireBusinessServiceApplication.class, args);
	}
}
