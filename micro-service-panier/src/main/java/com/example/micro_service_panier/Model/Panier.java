package com.example.micro_service_panier.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "panier", schema = "panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "item_details", nullable = false)
    private String itemDetails;

    @Column(name = "`disponibilité`")
    private Boolean disponibilité;

    @Column(name = "`quantité`", nullable = false)
    private Integer quantité;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "a_retirer")
    private Instant aRetirer;

    @Column(name = "nom_panier")
    private String nomPanier;

    public Panier() {

    }

    public Instant getaRetirer() {
        return aRetirer;
    }

    public void setaRetirer(Instant aRetirer) {
        this.aRetirer = aRetirer;
    }

    public Boolean getDisponibilité() {
        return disponibilité;
    }

    public void setDisponibilité(Boolean disponibilité) {
        this.disponibilité = disponibilité;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public String getNomPanier() {
        return nomPanier;
    }

    public void setNomPanier(String nomPanier) {
        this.nomPanier = nomPanier;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getQuantité() {
        return quantité;
    }

    public void setQuantité(Integer quantité) {
        this.quantité = quantité;
    }

    public Panier(String nomPanier,String itemDetails,Boolean disponibilité,  Instant aRetirer,  BigDecimal prix, Integer quantité) {
        this.aRetirer = aRetirer;
        this.disponibilité = disponibilité;
        this.id = id;
        this.itemDetails = itemDetails;
        this.nomPanier = nomPanier;
        this.prix = prix;
        this.quantité = quantité;
    }
}