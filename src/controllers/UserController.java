package controllers;

import dao.UserDAO;
import jdbc.factory.ConnectionFactory;

import java.sql.Connection;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        Connection connection = new ConnectionFactory().getConnection();
        this.userDAO = new UserDAO(connection);
    }


    public boolean login(String login, String password) {
        return this.userDAO.login(login, password);
    }

}
