package com.example.micro_service_commande.SAGA;


import java.io.Serializable;

public class SagaMessage implements Serializable {

    private String status;      // Status of the operation
    private int panierId;       // Panier ID
    private int requiredQte;    // Required quantity
    private int qte;            // Quantity available
    private boolean dispo;      // Availability flag

    // Constructors

    public SagaMessage() {
    }

    public SagaMessage(String status, int panierId, int requiredQte) {
        this.status = status;
        this.panierId = panierId;
        this.requiredQte = requiredQte;
    }

    public SagaMessage(String status, int panierId, int requiredQte, int qte, boolean dispo) {
        this.status = status;
        this.panierId = panierId;
        this.requiredQte = requiredQte;
        this.qte = qte;
        this.dispo = dispo;
    }

    // Getters and Setters
    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public long getPanierId() {
        return panierId;
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getRequiredQte() {
        return requiredQte;
    }

    public void setRequiredQte(int requiredQte) {
        this.requiredQte = requiredQte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SagaMessage{" +
                "status='" + status + '\'' +
                ", panierId=" + panierId +
                ", requiredQte=" + requiredQte +
                ", qte=" + qte +
                ", dispo=" + dispo +
                '}';
    }
}