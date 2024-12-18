package com.example.micro_service_utilisateur.Controllers;

import com.example.micro_service_utilisateur.Model.Utilisateur;
import com.example.micro_service_utilisateur.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UtilisateurRestController {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurRestController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @GetMapping("/Utilisateurs")
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/Utilisateurs/{id}")
    public Utilisateur getUtilisateur(@PathVariable("id") long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @PostMapping("/Utilisateurs")
    public Utilisateur addUtilisateur(@RequestBody Utilisateur u) {
        return utilisateurRepository.save(u);
    }
}

