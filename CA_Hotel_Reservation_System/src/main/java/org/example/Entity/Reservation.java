package org.example.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reservationId;

    String status;
    LocalDateTime checkIn;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    Payment payment;

    public Reservation() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}
