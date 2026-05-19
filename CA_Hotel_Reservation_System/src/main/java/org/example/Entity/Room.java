package org.example.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomId;

    String roomType;
    double price;
    boolean available;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<Reservation> reservations;

    public Room() {
    }

    public Room(String roomType, double price, boolean available) {
        this.roomType = roomType;
        this.price = price;
        this.available = available;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}


