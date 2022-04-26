package com.backend.proyectoweb.proyectoweb_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan("com.backend.proyectoweb.proyectoweb_backend")
@EntityScan("com.backend.proyectoweb.proyectoweb_backend")
@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootApplication
public class ProyectoWeb_BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoWeb_BackendApplication.class, args);
	}

}
