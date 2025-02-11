package com.example.Controllers;

import com.example.Services.OrchestrateurService;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orchestrator")
public class OrchestrateurController {

    private final OrchestrateurService orderOrchestrator;

    public OrchestrateurController(OrchestrateurService orderOrchestrator) {
        this.orderOrchestrator = orderOrchestrator;
    }

    @PostMapping("/start/{panierId}/{quantity}")
    public Mono<String> startSaga(@PathVariable int panierId, @PathVariable int quantity) {
        return orderOrchestrator.startOrderSaga(panierId, quantity);
    }
}
