package controllers;

import dao.BookingDAO;
import jdbc.factory.ConnectionFactory;
import model.Booking;

import java.sql.Connection;
import java.util.List;

public class BookingController {
    private BookingDAO bookingDAO;


    public BookingController() {
        Connection connection = new ConnectionFactory().getConnection();
        this.bookingDAO = new BookingDAO(connection);
    }

    public void save(Booking booking) {
        this.bookingDAO.save(booking);
    }


    public void delete(Long id) {
        this.bookingDAO.delete(id);
    }

    public List<Booking> reservationList() {
       return this.bookingDAO.findAll();
    }

    public Booking findById(Long id) {
        return this.bookingDAO.findById(id);
    }


    public void updateReservation(long id, String checkIn, String checkOut, Long valor, String payment) {
        this.bookingDAO.updateReservation(id, checkIn, checkOut, valor, payment);
    }


    public Booking findReservationById(long i) {
        return this.bookingDAO.findById(i);
    }
}
