package com.crossover.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Configures and boots the application.
 */

@EnableCaching
@SpringBootApplication
public class ExchangeRateProviderApp {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateProviderApp.class, args);
	}
}
