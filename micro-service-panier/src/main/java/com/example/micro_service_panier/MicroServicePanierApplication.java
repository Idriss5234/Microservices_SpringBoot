package com.example.micro_service_panier;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@SpringBootApplication
@EnableRabbit  // Enable RabbitMQ support
@EnableCaching


public class MicroServicePanierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicePanierApplication.class, args);
	}

	@Bean
	ApplicationRunner start(PanierRepository repository) {
		return args -> {
			repository.save(new Panier(
					"Patisserie ",                  // nomPanier
					"Pain, tartes, croissants",          // itemDetails
					true,                                 // disponibilité
					Timestamp.from(Instant.now()),                   // aRetirer
					3.49,              // prix
					8                                     // quantité
			));
		};
}}
