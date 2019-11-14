package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo.metier")
@ComponentScan("com.example.demo.web")
@ComponentScan("com.example.demo.entities")
@ComponentScan("com.example.demo.security")
public class MaSuperBanqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaSuperBanqueApplication.class, args);
	}

}
