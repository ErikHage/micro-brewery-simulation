package com.tfr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MicroBreweryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroBreweryApplication.class, args);
	}
}
