package org.example;

import org.example.DAO.HotelOperations;

public class Main {
    public static void main(String[] args) {
        HotelOperations operations = new HotelOperations();
        operations.findAvailableRooms();
        operations.fetchRoomsByPrice(2000, 5000);
        operations.mostBookedRoomType();
        operations.deleteCancelledReservations();
    }
}