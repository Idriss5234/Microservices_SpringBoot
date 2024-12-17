package com.example.micro_service_panier;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;

@SpringBootApplication
public class MicroServicePanierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicePanierApplication.class, args);
	}

	@Bean
	ApplicationRunner start(PanierRepository repository) {
		return args -> {
			repository.save(new Panier(
					"Fruits et légumes",                  // nomPanier
					"Pommes, bananes, carottes",          // itemDetails
					true,                                 // disponibilité
					Instant.now(),                        // aRetirer
					new BigDecimal("4.99"),              // prix
					5                                     // quantité
			));
		};
}}
