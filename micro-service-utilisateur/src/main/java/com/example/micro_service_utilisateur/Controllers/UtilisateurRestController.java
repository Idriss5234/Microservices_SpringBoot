package com.example.micro_service_utilisateur.Controllers;

import com.example.micro_service_utilisateur.Model.Utilisateur;
import com.example.micro_service_utilisateur.Services.UtilisateurService;
import com.example.micro_service_utilisateur.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Utilisateurs")
public class UtilisateurRestController {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurRestController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @GetMapping("/Recuperer")
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @PostMapping("/Ajouter")
    public Utilisateur addUtilisateur(@RequestBody Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @GetMapping("/{id}/exists")
    public boolean checkUserExists(@PathVariable int id) {
        return UtilisateurService.userExists(id);
    }
}

