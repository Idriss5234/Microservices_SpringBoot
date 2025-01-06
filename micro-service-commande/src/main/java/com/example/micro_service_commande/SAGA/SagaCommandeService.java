package com.example.micro_service_commande.SAGA;


import com.example.micro_service_commande.Model.Commande;
import com.example.micro_service_commande.Repository.CommandeRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class SagaCommandeService {

    int userID;
    Long commandeID;
    private final RabbitTemplate rabbitTemplate;
    private final CommandeRepository commandeRepository;

    public SagaCommandeService(RabbitTemplate rabbitTemplate, CommandeRepository commandeRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.commandeRepository = commandeRepository;
    }

    /**
     * Starts the Saga for processing Commande.
     */
    public void startCommandeSaga(int userId, int panierId, int requiredQuantity) {
       System.out.println("Starting Commande Saga...");

        Commande commande = new Commande();
        commande.setUserId(userID);
        commande.setPanierId(panierId);
        commande.setQuantité(requiredQuantity);
        commande.setPrix(0);  // Placeholder for price calculation logic
        commande.setStatut("Pending");
        commande.setDate(new java.sql.Timestamp(System.currentTimeMillis()).toInstant());

        Commande savedCommande = commandeRepository.save(commande);
        commandeID= Long.valueOf(savedCommande.getId());
        userID = userId;
        SagaMessage sagaMessage = new SagaMessage("success", panierId, requiredQuantity,0.00);

        rabbitTemplate.convertAndSend("saga-exchange", "api1-consumer-routing-key", sagaMessage);
    }

    /**
     * Handles the response from the Panier service.
     */
    @RabbitListener(queues = "api1-producer-queue")
    public void handleCommandeGetResponse(SagaMessage message) {
        System.out.println("Commande Get Response: " + message.getStatus());
        if ("success".equals(message.getStatus())) {
            System.out.println("Commande validated. Proceeding to update...");
            rabbitTemplate.convertAndSend("saga-exchange", "api2-consumer-routing-key", new SagaMessage(
                    "UPDATE_PANIER",
                    (int) message.getPanierId(),
                    message.getRequiredQte(),
                    message.getPanierPrix()
            ));
        } else {
            System.out.println("Commande validation failed.");
        }
    }

    /**
     * Handles the final update in Commande service.
     */



    @RabbitListener(queues = "api2-producer-queue")
    public void handleCommandePostResponse(SagaMessage message) {
        System.out.println("Commande Update Response: " + message.getStatus());
        if ("Panier Succefully updated".equals(message.getStatus())) {

                Commande commande = commandeRepository.findById(commandeID).orElse(null);
                commande.setStatut("Completed");
                commandeRepository.save(commande);
                System.out.println("Commande successfully updated.");
        } else {
            System.out.println("Commande update failed.");
            Commande commande = commandeRepository.findById(commandeID).orElse(null);
            commande.setStatut("CANCELLED");
            commandeRepository.save(commande);
            System.out.println("Commande successfully updated.");
        }
    }
}