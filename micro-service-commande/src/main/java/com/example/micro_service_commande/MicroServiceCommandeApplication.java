package com.example.micro_service_commande;

import com.example.micro_service_commande.Model.Commande;
import com.example.micro_service_commande.Repository.CommandeRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
@EnableRabbit  // Enable RabbitMQ support

public class MicroServiceCommandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceCommandeApplication.class, args);
	}

	@Bean
	ApplicationRunner start(CommandeRepository repository) {
		return args -> {
			// Get tomorrow's date at 14:00 par exmple
			LocalDateTime tomorrowAt14 = LocalDateTime.now()
					.plusDays(1)              // Add one day to the current date
					.withHour(14)             // Set the hour to 14:00
					.withMinute(0)            // Set the minute to 00
					.withSecond(0)            // Set the second to 00
					.withNano(0);             // Set the nanosecond to 00

			// Convert LocalDateTime to Instant
			Instant tomorrowAt14Instant = tomorrowAt14.atZone(ZoneId.systemDefault())  // Convert to the system default timezone
					.toInstant();

			repository.save(new Commande(
					tomorrowAt14Instant,            // date set to tomorrow at 14:00
					1,                              // userId (example: 1 for the first user)
					1,                            // panierId (example: 101 for a specific panier)
					2,                              // quantité (example: 3 items)
					19.99,        // prix (example: price of the items)
					"En cours"                      // statut (example: "In progress")
			)
			);
		};
}}
