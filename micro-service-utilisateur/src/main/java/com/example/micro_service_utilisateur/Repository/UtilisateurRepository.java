package com.example.micro_service_utilisateur.Repository;

import com.example.micro_service_utilisateur.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
