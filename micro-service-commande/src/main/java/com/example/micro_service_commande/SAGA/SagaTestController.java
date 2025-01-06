package com.example.micro_service_commande.SAGA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-saga")
public class SagaTestController {

    private final SagaCommandeService sagaCommandeService;

    public SagaTestController(SagaCommandeService sagaCommandeService) {
        this.sagaCommandeService = sagaCommandeService;
    }

    @GetMapping("/{panierId}/{quantity}")
    public ResponseEntity<String> testSaga(@PathVariable int panierId, @PathVariable int quantity) {
        sagaCommandeService.startCommandeSaga(1,panierId, quantity);
        return ResponseEntity.ok("Saga started for Panier ID: " + panierId);
    }
}
