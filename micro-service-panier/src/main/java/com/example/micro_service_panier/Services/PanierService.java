package com.example.micro_service_panier.Services;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierService {

    private final PanierRepository panierRepository;

    private static final Logger logger = LoggerFactory.getLogger(PanierService.class);

    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public boolean isPanierAvailable(int id, int quantite) {
        Optional<Panier> panier = panierRepository.findById(Long.valueOf(id));
        return panier.isPresent() && panier.get().getQuantité() >= quantite;
    }

    public String updatePanier(int id, int quantite) {
        Optional<Panier> panierOpt = panierRepository.findById(Long.valueOf(id));
    
        if (panierOpt.isEmpty()) {
            return "❌ Panier introuvable.";
        }
    
        Panier panier = panierOpt.get();
    
        if (panier.getQuantité() < quantite) {
            return "⚠️ Stock insuffisant. Disponible : " + panier.getQuantité();
        }
    
        panier.setQuantité(panier.getQuantité() - quantite);
        panierRepository.save(panier);
        
        return "✅ Mise à jour réussie.";
    }
    
    

    /**
     * Récupérer le prix du panier
     */
    public double getPanierPrix(int panierId) {
        Optional<Panier> panierOpt = panierRepository.findById((long) panierId);
        if (panierOpt.isPresent()) {
            Panier panier = panierOpt.get();
            return panier.getPrix(); 
        } else {
            throw new IllegalArgumentException("Panier non trouvé avec l'ID: " + panierId);
        }
    }

    public void rollbackPanier(int id, int quantiteInitiale) {
        Optional<Panier> panierOpt = panierRepository.findById(Long.valueOf(id));
        if (panierOpt.isPresent()) {
            Panier panier = panierOpt.get();
            panier.setQuantité(panier.getQuantité() + quantiteInitiale); // Réajuster la quantité en rajoutant celle qui a été soustraite
            panierRepository.save(panier);
            logger.info("Quantité du panier {} réajustée à : {}", panier.getId(), panier.getQuantité());

        } else {
            logger.error("Panier avec ID {} non trouvé pour la compensation", id);
        }
    }
    
}
