package com.chakarapani.users;

import com.chakarapani.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "ms-users", description = "This Microservice contains user database",
		contact = @Contact(name = "Srinivasan Chakarapani", email = "chakarai1234@gmail.com"), version = "1.0.0"))
@EntityScan(basePackageClasses = {Users.class})
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper newObjMapper = new ObjectMapper();
		newObjMapper.registerModule(new JavaTimeModule());
		return newObjMapper;
	}
}
