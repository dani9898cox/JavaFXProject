package com.example.terogmergifx.repository;

import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.utils.Sha1Convertor;
import com.example.terogmergifx.validator.StrategieValidator;
import com.example.terogmergifx.validator.Validator;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBRepoUtilizatori extends RepoUtilizatori {
    Validator vali = new Validator(StrategieValidator.UTILIZATOR);

    private String url;
    private String username;
    private String password;

    public DBRepoUtilizatori(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.findAll();
    }

    public void findAll() {

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from \"Utilizator\"");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("IDUtilizator");
                String nume = resultSet.getString("NumeUtilizator");
                String parola = resultSet.getString("ParolaUtilizator");
                String email = resultSet.getString("Email");

                Utilizator utilizator = new Utilizator(id, nume,parola,email);
                users.add(utilizator);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addUser(Utilizator user) throws RepoException
    {
        super.addUser(user);
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {    PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Utilizator\"(\"IDUtilizator\",\"NumeUtilizator\",\"ParolaUtilizator\",\"Email\") VALUES (?,?,?,?)");
             statement.setString(1,user.getId());
             statement.setString(2,user.getName());
             statement.setString(3,user.getPassword());
             statement.setString(4,user.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilizator deleteUser(Utilizator user) throws RepoException {
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {    PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Utilizator\" SET WHERE \"IDUtilizator\" = (?) ");
            statement.setString(1,user.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RepoException("Utilizator inexistent");
        }
        this.users.clear();
        this.findAll();
        return  user;

    }

}
