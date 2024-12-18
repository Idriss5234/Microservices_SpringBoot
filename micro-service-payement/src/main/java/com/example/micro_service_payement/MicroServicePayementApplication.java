package com.example.micro_service_payement;

import com.example.micro_service_payement.Model.Payement;
import com.example.micro_service_payement.Repository.PayementRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;

@SpringBootApplication
public class MicroServicePayementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicePayementApplication.class, args);
	}

	@Bean
	ApplicationRunner start(PayementRepository repository) {
		return args -> {
			repository.save(new Payement(
					1,                          // commandeId (example: 1 for the first commande)
					Instant.now(),        // createdAt (set to tomorrow at 14:00 par exemple)
					new BigDecimal("19.99"),    // prix (example: 19.99)
					2,                          // userId (example: 2 for the second user)
					"Completed"                 // paymentStatus (example: "Completed")
			));
		};
}}
