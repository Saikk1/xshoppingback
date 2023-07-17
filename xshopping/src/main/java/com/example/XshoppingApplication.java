package com.example;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class XshoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(XshoppingApplication.class, args);
	}

}
