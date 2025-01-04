package com.example.micro_service_panier.SAGA;
import com.example.micro_service_panier.Model.Panier;
import com.example.micro_service_panier.Repository.PanierRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaPanierService {

    private final RabbitTemplate rabbitTemplate;
    private final PanierRepository panierRepository;

    public SagaPanierService(RabbitTemplate rabbitTemplate, PanierRepository panierRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.panierRepository = panierRepository;
    }

    /**
     * Starts the Saga for checking Panier.
     */
    public void startPanierSaga(int panierId, int requiredQuantity) {
        System.out.println("Starting Panier Saga...");
        Panier panier = panierRepository.findById((long) panierId).orElse(null);
        if (panier != null) {
            SagaMessage sagaMessage = new SagaMessage(
                    "CHECK_PANIER",
                    panierId,
                    requiredQuantity,
                    panier.getQuantité(),
                    panier.getDisponibilité()
            );
            rabbitTemplate.convertAndSend("saga-exchange", "panier-get-routing-key", sagaMessage);
        } else {
            System.out.println("Panier not found in the database.");
        }
    }

    /**
     * Handles the response from the get API (Panier Check).
     */
    @RabbitListener(queues = "panier-api-get-consumer-queue")
    public void handlePanierGetResponse(SagaMessage message) {
        System.out.println("Panier Check Response: " + message.getStatus());
        if ("success".equals(message.getStatus()) && message.getQte() >= message.getRequiredQte()) {
            System.out.println("Panier validation successful. Proceeding to Commande...");
            rabbitTemplate.convertAndSend("saga-exchange", "commande-routing-key", new SagaMessage(
                    "PROCESS_COMMANDE",
                    (int) message.getPanierId(),
                    message.getRequiredQte()
            ));
        } else {
            System.out.println("Panier validation failed. Insufficient quantity or unavailable.");
        }
    }

    /**
     * Handles the update after the Commande service processes the request.
     */
    @RabbitListener(queues = "panier-api-post-consumer-queue")
    public void handlePanierPostResponse(SagaMessage message) {
        System.out.println("Panier Update Response: " + message.getStatus());
        if ("success".equals(message.getStatus())) {
            System.out.println("Updating Panier in the database...");
            Panier panier = panierRepository.findById(message.getPanierId()).orElse(null);
            if (panier != null) {
                panier.setQuantité(panier.getQuantité() - message.getRequiredQte());
                panierRepository.save(panier);
                System.out.println("Panier successfully updated. Saga completed.");
            } else {
                System.out.println("Panier not found for update.");
            }
        } else {
            System.out.println("Panier update failed. Compensation may be required.");
        }
    }
}