package com.didem.system_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.didem.system_rest_api"})
@EntityScan(basePackages = {"com.didem.system_rest_api"})
@EnableJpaRepositories(basePackages = {"com.didem.system_rest_api"})
@SpringBootApplication
public class SystemRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemRestApiApplication.class, args);
	}

}
