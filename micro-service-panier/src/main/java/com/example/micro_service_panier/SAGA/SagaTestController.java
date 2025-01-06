package com.example.micro_service_panier.SAGA;

import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-saga")
public class SagaTestController {

    private final SagaPanierService sagaPanierService;

    public SagaTestController(SagaPanierService sagaPanierService) {
        this.sagaPanierService = sagaPanierService;
    }

   /* @GetMapping("/{panierId}/{quantity}")
    public ResponseEntity<String> testSaga(@PathVariable int panierId, @PathVariable int quantity) {
        sagaPanierService.startPanierSaga(panierId, quantity);
        return ResponseEntity.ok("Saga started for Panier ID: " + panierId);
    }*/
}
