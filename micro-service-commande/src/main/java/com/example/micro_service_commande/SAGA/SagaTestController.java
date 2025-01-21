package com.example.micro_service_commande.SAGA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test-saga")
public class SagaTestController {

    private final SagaCommandeService sagaCommandeService;
    private final RestTemplate restTemplate;

    @Autowired
    public SagaTestController(SagaCommandeService sagaCommandeService, RestTemplate restTemplate) {
        this.sagaCommandeService = sagaCommandeService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("{userId}/{panierId}/{quantity}")
    public ResponseEntity<String> testSaga(@PathVariable int userId, @PathVariable int panierId, @PathVariable int quantity) {
        // Fetch user from User microservice
        String userUrl = "http://utilisateur:8090/Utilisateurs/" + userId;
        ResponseEntity<String> userResponse = restTemplate.getForEntity(userUrl, String.class);
        System.out.println(userResponse);
        System.out.println(userResponse.getHeaders().getContentLength());


        if (userResponse.getHeaders().getContentLength() != 0) {
            // User exists, proceed with the saga
            sagaCommandeService.startCommandeSaga(userId, panierId, quantity);
            return ResponseEntity.ok("Saga started for Panier ID: " + panierId);
        } else {
            System.out.println("User not found");
            // User not found or error occurred
            return ResponseEntity.status(userResponse.getStatusCode()).body("Error: " + userResponse.getBody());
            
        }
    }
}
