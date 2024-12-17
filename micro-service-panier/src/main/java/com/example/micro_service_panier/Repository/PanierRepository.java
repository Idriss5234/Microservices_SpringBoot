package com.example.micro_service_panier.Repository;

import com.example.micro_service_panier.Model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

}
