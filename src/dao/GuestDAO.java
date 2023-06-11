package dao;

import model.Guest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuestDAO {
    private final Connection connection;

    public GuestDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Guest guest) {
        String sql = "insert into hospedes (nome, sobrenome, data_nascimento, nascionalidade, telefone, id_reserva) values (?, ?, ?, ?, ?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, guest.getNome());
            statement.setString(2, guest.getSobrenome());
            statement.setDate(3, new Date(guest.getDataNascimento().getTime()));
            statement.setString(4, guest.getNacionalidade());
            statement.setString(5, guest.getTelefone());
            statement.setLong(6, guest.getIdReserva());

            statement.execute();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    guest.setId(resultSet.getLong(1));
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void update(Guest guest) {
        String sql = "update hospedes set nome = ?, sobrenome = ?, data_nascimento = ?, nascionalidade = ?, telefone = ?, id_reserva = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, guest.getNome());
            statement.setString(2, guest.getSobrenome());
            statement.setDate(3, new Date(guest.getDataNascimento().getTime()));
            statement.setString(4, guest.getNacionalidade());
            statement.setString(5, guest.getTelefone());
            statement.setLong(6, guest.getIdReserva());
            statement.setLong(7, guest.getId());

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(Long id) {
        String sql = "delete from hospedes where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Guest findById(Long id) {
        String sql = "select * from hospedes where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next()) {
                    Guest guest = new Guest();
                    guest.setId(resultSet.getLong("id"));
                    guest.setNome(resultSet.getString("nome"));
                    guest.setSobrenome(resultSet.getString("sobrenome"));
                    guest.setDataNascimento(resultSet.getDate("data_nascimento"));
                    guest.setNacionalidade(resultSet.getString("nascionalidade"));
                    guest.setTelefone(resultSet.getString("telefone"));
                    guest.setIdReserva(resultSet.getLong("id_reserva"));

                    return guest;
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Guest> findBySobrenome(String sobrenome) {
        String sql = "select * from hospedes where sobrenome = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sobrenome);

            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next()) {
                    Guest guest = new Guest();
                    guest.setId(resultSet.getLong("id"));
                    guest.setNome(resultSet.getString("nome"));
                    guest.setSobrenome(resultSet.getString("sobrenome"));
                    guest.setDataNascimento(resultSet.getDate("data_nascimento"));
                    guest.setNacionalidade(resultSet.getString("nascionalidade"));
                    guest.setTelefone(resultSet.getString("telefone"));
                    guest.setIdReserva(resultSet.getLong("id_reserva"));

                    return Collections.singletonList(guest);
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Guest> findAll() {
        List<Guest> guests = new ArrayList<>();

        String sql = "select * from hospedes";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();

            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    Guest guest = new Guest();
                    guest.setId(resultSet.getLong("id"));
                    guest.setNome(resultSet.getString("nome"));
                    guest.setSobrenome(resultSet.getString("sobrenome"));
                    guest.setDataNascimento(resultSet.getDate("data_nascimento"));
                    guest.setNacionalidade(resultSet.getString("nascionalidade"));
                    guest.setTelefone(resultSet.getString("telefone"));
                    guest.setIdReserva(resultSet.getLong("id_reserva"));

                    guests.add(guest);
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guests;
    }

    public void updateName(long id, String nome) {
        String sql = "update hospedes set nome = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setLong(2, id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateLastName(long id, String sobrenome) {
        String sql = "update hospedes set sobrenome = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sobrenome);
            statement.setLong(2, id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateNationality(long id, String nacionalidade) {
        String sql = "update hospedes set nascionalidade = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nacionalidade);
            statement.setLong(2, id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateBornDate(long id, String dataNascimento) {
        String sql = "update hospedes set data_nascimento = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dataNascimento);
            statement.setLong(2, id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updatePhone(long id, String telefone) {
        String sql = "update hospedes set telefone = ? where id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, telefone);
            statement.setLong(2, id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


