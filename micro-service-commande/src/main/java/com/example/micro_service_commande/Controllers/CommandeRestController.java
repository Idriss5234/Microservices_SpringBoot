package com.example.micro_service_commande.Controllers;

import com.example.micro_service_commande.Model.Commande;
import com.example.micro_service_commande.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommandeRestController {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeRestController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }


    @GetMapping("/Commandes")
    public List<Commande> getCommandes() {
        return commandeRepository.findAll();
    }

    @GetMapping("/Commandes/{id}")
    public Commande getCommande(@PathVariable("id") long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @PostMapping("/Commandes")
    public Commande addCommande(@RequestBody Commande c) {
        return commandeRepository.save(c);
    }
}

