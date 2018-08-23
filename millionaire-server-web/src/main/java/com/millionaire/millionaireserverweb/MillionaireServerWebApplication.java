package com.millionaire.millionaireserverweb;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"com.millionaire.millionaireserverweb",
                                "com.millionaire.millionairemanagerservice",
                                "com.millionaire.millionairebusinessservice",
                                "com.millionaire.millionaireuserservice"})
public class MillionaireServerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireServerWebApplication.class, args);
	}
}
