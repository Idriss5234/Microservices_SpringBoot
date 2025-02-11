package com.example.Services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrchestrateurService {

    private final WebClient webClient;

    public OrchestrateurService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Début du workflow Saga
     */
    private static final Logger logger = LoggerFactory.getLogger(OrchestrateurService.class);

public Mono<String> startOrderSaga(int panierId, int quantity) {
    logger.info("Début de la saga pour Panier ID: {}, Quantité: {}", panierId, quantity);
    
    return checkPanierAvailability(panierId, quantity)
        .doOnNext(isAvailable -> logger.info("Disponibilité du panier: {}", isAvailable))
        .flatMap(isAvailable -> {
            if (isAvailable) {
                return updatePanier(panierId, quantity)
                    .doOnNext(response -> logger.info("Mise à jour du panier: {}", response))
                    .flatMap(response -> {
                        if (response.startsWith("✅")) {
                            return getPanierPrix(panierId)
                                .doOnNext(prix -> logger.info("Prix du panier récupéré: {}", prix))
                                .flatMap(prix -> createOrder(panierId, quantity, prix)
                                .thenReturn("✅ Commande créée avec succès"));
                        } else {
                            return Mono.just(response);
                        }
                    });
            } else {
                return Mono.just("❌ Panier non disponible.");
            }
        })
        .onErrorResume(error -> {
            logger.error("Erreur dans la saga : {}", error.getMessage());
            return compensatePanier(panierId, quantity)
                .thenReturn("⚠️ Erreur Saga : Compensation effectuée.");
        });
}
    

    /**
     * Vérifier la disponibilité du panier
     */
    private Mono<Boolean> checkPanierAvailability(int panierId, int quantity) {
        return webClient.get()
                .uri("http://localhost:8091/api/paniers/{id}/disponible?quantite={qte}", panierId, quantity)
                .retrieve()
                .bodyToMono(Boolean.class);
    }


    private Mono<String> updatePanier(int panierId, int quantity) {
        Map<String, Object> panierData = Map.of(
            "panierId", panierId,
            "quantity", quantity
        );
    
        return webClient.post()
                .uri("http://localhost:8091/api/paniers/update")
                .bodyValue(panierData)
                .retrieve()
                .bodyToMono(String.class);
    }
    

    private Mono<Double> getPanierPrix(int panierId) {
        return webClient.get()
                .uri("http://localhost:8091/api/paniers/{id}/prix", panierId)  // Nouvelle API pour récupérer le prix
                .retrieve()
                .bodyToMono(Double.class);
    }

    /**
     * Créer la commande
     */
    private Mono<Void> createOrder(int panierId, int quantity, double prix) {
        double totalPrix = prix * quantity;
        Map<String, Object> commandeData = Map.of(
            "userId", 1,
            "panierId", panierId,
            "quantité", quantity,
            "prix", totalPrix
        );
    
        logger.info("📦 Envoi de la commande : {}", commandeData);
    
        return webClient.post()
                .uri("http://localhost:8092/api/commandes/createOrder")
                .bodyValue(commandeData)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(response -> logger.info("✅ Commande créée avec succès."))
                .doOnError(error -> logger.error("❌ Erreur lors de la création de la commande : {}", error.getMessage()));
    }
    
    

    /**
     * Compensation en cas d’échec
     */
    /**
 * Compensation en cas d’échec
 */
private Mono<Void> compensatePanier(int panierId, int quantitéInitiale) {
    return webClient.post()
            .uri("http://localhost:8091/api/paniers/rollback/{id}?quantite={qte}", panierId, quantitéInitiale)
            .retrieve()
            .bodyToMono(Void.class)
            .doOnSuccess(response -> logger.info("♻️ Compensation du panier {} effectuée, quantité réajustée.", panierId))
            .doOnError(error -> logger.error("⚠️ Erreur lors de la compensation du panier : {}", error.getMessage()));
}

}
