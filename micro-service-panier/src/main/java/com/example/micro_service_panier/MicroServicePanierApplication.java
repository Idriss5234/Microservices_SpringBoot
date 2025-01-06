package com.example.micro_service_panier;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@SpringBootApplication
@EnableRabbit  // Enable RabbitMQ support

public class MicroServicePanierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicePanierApplication.class, args);
	}

	@Bean
	ApplicationRunner start(PanierRepository repository) {
		return args -> {
			/*repository.save(new Panier(
					"Fruits ",                  // nomPanier
					"Pommes, bananes",          // itemDetails
					true,                                 // disponibilité
					Timestamp.from(Instant.now()),                   // aRetirer
					2.99,              // prix
					10                                     // quantité
			));*/
		};
}}
