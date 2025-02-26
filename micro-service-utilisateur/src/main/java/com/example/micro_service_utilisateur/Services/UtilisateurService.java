package com.example.micro_service_utilisateur.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.micro_service_utilisateur.Repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private static UtilisateurRepository UtilisateurRepository;
    
    public UtilisateurService(UtilisateurRepository UtilisateurRepository) {
        this.UtilisateurRepository = UtilisateurRepository;
    }

    public static boolean userExists(Integer id) {
        return UtilisateurRepository.existsById(id);
    }
}


