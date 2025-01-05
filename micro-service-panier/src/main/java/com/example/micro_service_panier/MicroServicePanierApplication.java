package com.example.micro_service_panier;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;

@SpringBootApplication
@EnableRabbit  // Enable RabbitMQ support

public class MicroServicePanierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicePanierApplication.class, args);
	}

}
