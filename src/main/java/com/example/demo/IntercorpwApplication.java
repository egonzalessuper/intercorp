package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages="com.example.demo,com.example.service,com.example.serviceImpl")
//@ComponentScan(basePackages={"com.example.demo","com.example.service","com.example.serviceImpl","com.example.repository"})
@ComponentScan(basePackages={"com.example.demo","com.example.service","com.example.serviceImpl","com.example.swagger"})
@EntityScan("com.example.dominio")
@EnableJpaRepositories("com.example.repository")
public class IntercorpwApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(IntercorpwApplication.class, args);
	}

}
