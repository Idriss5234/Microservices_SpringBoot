package com.example.micro_service_payement;

import com.example.micro_service_payement.Model.Payement;
import com.example.micro_service_payement.Repository.PayementRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;

@SpringBootApplication
@EnableCaching

public class MicroServicePayementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicePayementApplication.class, args);
	}

	@Bean
	ApplicationRunner start(PayementRepository repository) {
		return args -> {
			repository.save(new Payement(
					1,
					Instant.now(),
					new BigDecimal("19.99"),
					2,
					"Completed"
			));
		};
}}
