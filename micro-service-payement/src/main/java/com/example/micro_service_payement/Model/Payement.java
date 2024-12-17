package com.example.micro_service_payement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payement", schema = "payement")
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "commande_id", nullable = false)
    private Integer commandeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @ColumnDefault("'Pending'")
    @Column(name = "payment_status", length = 50)
    private String paymentStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    public Payement(Integer commandeId, Instant createdAt,  BigDecimal prix, Integer userId,String paymentStatus) {
        this.commandeId = commandeId;
        this.createdAt = createdAt;
        this.id = id;
        this.paymentStatus = paymentStatus;
        this.prix = prix;
        this.userId = userId;
    }

    public Payement() {

    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}