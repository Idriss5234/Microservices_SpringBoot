package com.example.micro_service_commande.Controllers;

import com.example.micro_service_commande.Model.Commande;
import com.example.micro_service_commande.Services.CommandeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

 @PostMapping("/createOrder")
public ResponseEntity<?> createOrder(@RequestBody Commande request) {
    if (request.getPanierId() == null || request.getQuantité() == null || request.getPrix() == 0) {
        return ResponseEntity.badRequest().body("⚠️ Paramètres invalides pour créer la commande.");
    }

    Commande newOrder = commandeService.createOrder(
            request.getUserId() != null ? request.getUserId() : 1, 
            request.getPanierId(), 
            request.getQuantité(), 
            request.getPrix()
    );

    return ResponseEntity.ok(newOrder);
}


}
