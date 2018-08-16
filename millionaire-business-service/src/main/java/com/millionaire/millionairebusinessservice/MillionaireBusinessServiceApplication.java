package com.millionaire.millionairebusinessservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.millionaire.millionairebusinessservice.dao")
public class MillionaireBusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireBusinessServiceApplication.class, args);
	}
}
