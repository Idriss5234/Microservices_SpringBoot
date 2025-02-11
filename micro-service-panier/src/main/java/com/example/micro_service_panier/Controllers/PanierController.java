package com.example.micro_service_panier.Controllers;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import com.example.micro_service_panier.Services.PanierService;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @GetMapping("/{id}/disponible")
    public boolean checkPanierAvailability(@PathVariable int id, @RequestParam int quantite) {
        return panierService.isPanierAvailable(id, quantite);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePanier(@RequestBody Map<String, Object> request) {
    try {
        int panierId = (int) request.get("panierId");
        int quantity = (int) request.get("quantity");

        String response = panierService.updatePanier(panierId, quantity);

        if (response.startsWith("✅")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("❌ Erreur lors de la mise à jour du panier.");
    }
}


@PostMapping("/rollback/{id}")
public void rollbackPanier(@PathVariable int id, @RequestParam int quantite) {
    panierService.rollbackPanier(id, quantite);  // Passage de la quantité à réajuster
}


    @GetMapping("/{id}/prix")
    public double getPanierPrice(@PathVariable int id) {
        return panierService.getPanierPrix(id);  // Appeler un service pour calculer et retourner le prix
    }
}
