package com.example.micro_service_commande.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "commande", schema = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "panier_id", nullable = false)
    private Integer panierId;

    @Column(name = "`quantité`", nullable = false)
    private Integer quantité;

    @Column(name = "prix", nullable = false)
    private double prix;

    @ColumnDefault("'Pending'")
    @Column(name = "statut", length = 50)
    private String statut;

    @Column(name = "date")
    private Instant date;

    public Commande(Instant date,Integer userId, Integer panierId, Integer quantité,double prix, String statut) {
        this.date = date;
        this.panierId = panierId;
        this.prix = prix;
        this.quantité = quantité;
        this.statut = statut;
        this.userId = userId;
    }

    public Commande() {

    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPanierId() {
        return panierId;
    }

    public void setPanierId(Integer panierId) {
        this.panierId = panierId;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Integer getQuantité() {
        return quantité;
    }

    public void setQuantité(Integer quantité) {
        this.quantité = quantité;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}