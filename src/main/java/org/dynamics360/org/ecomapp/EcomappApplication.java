package org.dynamics360.org.ecomapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcomappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomappApplication.class, args);
	}

}
