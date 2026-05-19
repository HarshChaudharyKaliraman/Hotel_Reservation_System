package org.example.Entity;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int paymentId;

    int amount;
    String paymentStatus;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    public Payment() {
    }

    public Payment(int amount, String paymentStatus) {
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
