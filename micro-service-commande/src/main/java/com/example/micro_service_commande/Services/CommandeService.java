package com.example.micro_service_commande.Services;

import com.example.micro_service_commande.Model.Commande;
import com.example.micro_service_commande.Repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public Commande createOrder(int userId, int panierId, int quantite, double totalPrix) {
        Commande commande = new Commande();
        commande.setUserId(userId);
        commande.setPanierId(panierId);
        commande.setQuantité(quantite);
        commande.setStatut("Created");
        commande.setDate(Instant.now());
        commande.setPrix(totalPrix);
    
    
        return commandeRepository.save(commande);
    }
    
    
}
