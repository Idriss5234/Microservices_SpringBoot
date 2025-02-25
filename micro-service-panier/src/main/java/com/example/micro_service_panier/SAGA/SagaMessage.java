package com.example.micro_service_panier.SAGA;

import java.io.Serializable;

public class SagaMessage implements Serializable {

    private String status;      // Status de l'operation
    private int panierId;       // ID Panier 
    private int requiredQte;    // quantité demandée
    private int qte;            // Quantité disponible
    private boolean dispo;      // Disponibilité flag
    private double panierPrix;  // Prix Panier



    // Constructeurs
    public SagaMessage() {
    }

    public SagaMessage(String status) {
        this.status = status;

    }

    public SagaMessage(String status, int panierId, int requiredQte,double panierPrix, int qte) {
        this.status = status;
        this.panierId = panierId;
        this.requiredQte = requiredQte;
        this.panierPrix=panierPrix;
        this.qte=qte;

    }

    public SagaMessage(String status, int panierId, int requiredQte,double panierPrix) {
        this.status = status;
        this.panierId = panierId;
        this.requiredQte = requiredQte;
        this.panierPrix=panierPrix;

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

    public double getPanierPrix() {
        return panierPrix;
    }

    public void setPanierPrix(double panierPrix) {
        this.panierPrix = panierPrix;
    }

    @Override
    public String toString() {
        return "SagaMessage{" +
                "status='" + status + '\'' +
                ", panierId=" + panierId +
                ", requiredQte=" + requiredQte +
                ", panierPrix=" + panierPrix +
                ", qte=" + qte +
                '}';
    }
}