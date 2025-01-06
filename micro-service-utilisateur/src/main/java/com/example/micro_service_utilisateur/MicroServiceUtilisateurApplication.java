package com.example.micro_service_utilisateur;

import com.example.micro_service_utilisateur.Model.Utilisateur;
import com.example.micro_service_utilisateur.Repository.UtilisateurRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroServiceUtilisateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceUtilisateurApplication.class, args);
	}

	
	@Bean
	ApplicationRunner start(UtilisateurRepository repository) {
		return args -> {
			//repository.save(new Utilisateur("Mouilly","Fatima Ezzahrae","mouilly@gmail.com","5555","A4554484"));
		};
}}
