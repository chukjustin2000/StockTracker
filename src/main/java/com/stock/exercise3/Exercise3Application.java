package com.stock.exercise3;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Exercise3Application {

	public static void main(String[] args) {
		SpringApplication.run(Exercise3Application.class, args);
	}

	@Bean
	public ApplicationRunner booksInitializer(StockService stockService) {
		LocalDateTime now = LocalDateTime.now();
		return args -> {
			stockService.create(
					new Stock(1, "MTN", 90.00, now, now ));
			stockService.create(
					new Stock(2, "NB", 230.40, now, now));
			stockService.create(
					new Stock(3, "Guinness", 255.00, now, now));
			stockService.create(
					new Stock(3, "Dangote Sugar", 70.00, now, now));
		};
	}
}
