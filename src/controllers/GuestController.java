package controllers;

import dao.GuestDAO;
import jdbc.factory.ConnectionFactory;
import model.Guest;

import java.sql.Connection;
import java.util.List;

public class GuestController {

    private final GuestDAO guestDAO;


    public GuestController() {
        Connection connection = new ConnectionFactory().getConnection();
        this.guestDAO = new GuestDAO(connection);
    }

    public void save(Guest guest) {
        this.guestDAO.save(guest);
    }

    public void update(Guest guest) {
        this.guestDAO.update(guest);
    }

    public void delete(Long id) {
        this.guestDAO.delete(id);
    }

    public void list() {
        this.guestDAO.findAll();
    }

    public Guest findById(Long id) {
      return  this.guestDAO.findById(id);
    }

    public List<Guest> findBySobrenome(String sobrenome) {
        return this.guestDAO.findBySobrenome(sobrenome);
    }

    public List<Guest> findAllGuests() {
        return this.guestDAO.findAll();
    }

    public void updateName(long id, String nome) {
        this.guestDAO.updateName(id, nome);
    }

    public void updateLastName(long id, String sobrenome) {
        this.guestDAO.updateLastName(id, sobrenome);
    }

    public void updateNationality(long id, String nacionalidade) {
        this.guestDAO.updateNationality(id, nacionalidade);
    }

    public void updateBornDate(long id, String dataNascimento) {
        this.guestDAO.updateBornDate(id, dataNascimento);
    }

    public void updatePhone(long id, String telefone) {
        this.guestDAO.updatePhone(id, telefone);
    }
}
