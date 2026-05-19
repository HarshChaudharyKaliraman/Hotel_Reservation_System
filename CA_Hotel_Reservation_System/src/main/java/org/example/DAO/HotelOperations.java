package org.example.DAO;

import org.example.Entity.*;
import org.example.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelOperations {

    // Fetch reservations by customer
    public void fetchReservationsByCustomer(int customerId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Reservation r WHERE r.customer.customerId = :cid";
        List<Reservation> list = s.createQuery(hql, Reservation.class)
                .setParameter("cid", customerId)
                .list();
        list.forEach(r ->
                System.out.println("Reservation ID: " + r.getReservationId()));
        s.close();
    }

    // Find available rooms
    public void findAvailableRooms() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Room WHERE available = true";
        List<Room> rooms = s.createQuery(hql, Room.class).list();
        rooms.forEach(r ->
                System.out.println(r.getRoomType()));
        s.close();
    }

    // Count reservations per room type
    public void countReservationsPerRoomType() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT r.room.roomType, COUNT(r) FROM Reservation r GROUP BY r.room.roomType";
        List<Object[]> result = s.createQuery(hql).list();
        for (Object[] row : result) {
            System.out.println(row[0] + " : " + row[1]);
        }
        s.close();
    }

    // Update reservation status
    public void updateReservationStatus(int reservationId, String status) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        String hql = "UPDATE Reservation SET status = :status WHERE reservationId = :rid";
        s.createMutationQuery(hql)
                .setParameter("status", status)
                .setParameter("rid", reservationId)
                .executeUpdate();
        t.commit();
        s.close();
    }

    // Delete cancelled reservations
    public void deleteCancelledReservations() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        String hql = "DELETE FROM Reservation WHERE status = 'Cancelled'";
        s.createMutationQuery(hql).executeUpdate();
        t.commit();
        s.close();
    }

    // Join query for customer-room details
    public void customerRoomDetails() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT c.name, r.roomType FROM Reservation rs JOIN rs.customer c JOIN rs.room r";
        List<Object[]> result = s.createQuery(hql).list();
        for (Object[] row : result) {
            System.out.println("Customer: " + row[0] +
                    " | Room: " + row[1]);
        }
        s.close();
    }

    // Fetch rooms by price range
    public void fetchRoomsByPrice(double min, double max) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Room WHERE price BETWEEN :min AND :max ";
        List<Room> rooms = s.createQuery(hql, Room.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();
        rooms.forEach(r ->
                System.out.println(r.getRoomType()));
        s.close();
    }

    // Find most booked room type
    public void mostBookedRoomType() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT r.room.roomType, COUNT(r) FROM Reservation r GROUP BY r.room.roomType ORDER BY COUNT(r) DESC ";
        List<Object[]> result = s.createQuery(hql)
                .setMaxResults(1)
                .list();
        for (Object[] row : result) {
            System.out.println("Most Booked: " +
                    row[0] + " -> " + row[1]);
        }
        s.close();
    }

    // Fetch unpaid reservations
    public void unpaidReservations() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = " FROM Reservation r WHERE r.payment.paymentStatus = 'Pending' ";
        List<Reservation> list = s.createQuery(hql, Reservation.class)
                .list();
        list.forEach(r ->
                System.out.println(r.getReservationId()));
        s.close();
    }

    // Search reservations by check-in date
    public void searchByCheckInDate(java.util.Date date) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = " FROM Reservation WHERE checkInDate = :date ";
        List<Reservation> list = s.createQuery(hql, Reservation.class)
                .setParameter("date", date)
                .list();
        list.forEach(r ->
                System.out.println(r.getReservationId()));
        s.close();
    }
}