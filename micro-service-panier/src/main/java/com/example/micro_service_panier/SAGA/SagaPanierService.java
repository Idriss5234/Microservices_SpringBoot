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
     * Handles the response from the get API (Panier Check).
     */
    @RabbitListener(queues = "api1-consumer-queue")
    public void handlePanierGetResponse(SagaMessage message) {
        System.out.println("Panier Check Response: " + message.getStatus());
        Panier panier= panierRepository.findById(message.getPanierId()).orElse(null);
        if ("success".equals(message.getStatus()) && panier!=null) {
            System.out.println("Panier exists. Proceeding to Commande...");
            System.out.println("Panier prix: "+panier.getPrix() );
            rabbitTemplate.convertAndSend("saga-exchange", "api1-producer-routing-key", new SagaMessage(
                    "success",
                    (int) message.getPanierId(),
                    message.getRequiredQte(),
                    panier.getPrix()
            ));
        } else {
            System.out.println("Panier validation failed. Insufficient quantity or unavailable.");
        }
    }

    /**
     * Handles the update after the Commande service processes the request.
     */





    @RabbitListener(queues = "api2-consumer-queue")
    public void handlePanierPostResponse(SagaMessage message) {
        System.out.println("Panier Update Response: " + message.getStatus());
        if ("UPDATE_PANIER".equals(message.getStatus())) {
            System.out.println("Updating Panier in the database...");
            Panier panier = panierRepository.findById(message.getPanierId()).orElse(null);
            if (panier != null) {
                panier.setQuantité(panier.getQuantité() - message.getRequiredQte());
                panierRepository.save(panier);
                System.out.println("Panier successfully updated. Saga completed.");
                rabbitTemplate.convertAndSend("saga-exchange", "api2-producer-routing-key", new SagaMessage(
                        "Panier Succefully updated",
                        (int) message.getPanierId(),
                        message.getRequiredQte(),
                        message.getPanierPrix()
                ));
            } else {
                System.out.println("Panier not found for update.");
            }
        } else {
            System.out.println("Panier update failed. Compensation may be required.");
        }
    }
}