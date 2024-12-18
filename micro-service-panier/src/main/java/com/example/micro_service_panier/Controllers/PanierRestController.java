package com.example.micro_service_panier.Controllers;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PanierRestController {

    private final PanierRepository panierRepository;

    @Autowired
    public PanierRestController(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }


    @GetMapping("/Paniers")
    public List<Panier> getPaniers() {
        return panierRepository.findAll();
    }

    @GetMapping("/Paniers/{id}")
    public Panier getPaniers(@PathVariable("id") long id) {
        return panierRepository.findById(id).orElse(null);
    }

    @PostMapping("/Paniers")
    public Panier addPanier(@RequestBody Panier p) {
        return panierRepository.save(p);
    }
}

