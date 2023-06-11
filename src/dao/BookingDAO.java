package dao;

import enuns.FormaPagamento;
import model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class BookingDAO {
    private final Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Booking booking) {
        String sql = "insert into reservas (data_entrada, data_saida, valor, forma_pagamento) values (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, new Date(booking.getcheckInDate().getTime()));
            statement.setDate(2, new Date(booking.getCheckOut().getTime()));
            statement.setDouble(3, booking.getValor());
            statement.setString(4, booking.getFormaPagamento().toString());

            statement.execute();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    booking.setId(resultSet.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void delete(Long id) {
        String sql = "delete from reservas where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> findAll() {
        List<Booking> reservations = new ArrayList<>();

        String sql = "select id,data_entrada, data_saida, valor, forma_pagamento from reservas";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    Booking booking = new Booking(
                            resultSet.getLong("id"),
                            resultSet.getDate("data_entrada"),
                            resultSet.getDate("data_saida"),
                            resultSet.getDouble("valor"),
                            resultSet.getString("forma_pagamento")
                    );

                    reservations.add(booking);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }


    public Booking findById(Long id) {
        String sql = "select * from reservas where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next()) {
                    Booking booking = new Booking();
                    booking.setId(resultSet.getLong("id"));
                    booking.setcheckInDate(resultSet.getDate("data_entrada"));
                    booking.setCheckOut(resultSet.getDate("data_saida"));
                    booking.setValor(resultSet.getDouble("valor"));
                    booking.setFormaPagamento(FormaPagamento.valueOf(resultSet.getString("forma_pagamento")));


                    return booking;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public void updateReservation(long id, String checkIn, String checkOut, Long valor, String payment) {
        String sql = "update reservas set data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(checkIn));
            statement.setDate(2, Date.valueOf(checkOut));
            statement.setDouble(3, valor);
            statement.setString(4, payment);
            statement.setLong(5, id);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
