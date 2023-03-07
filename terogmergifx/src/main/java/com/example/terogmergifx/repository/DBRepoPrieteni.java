package com.example.terogmergifx.repository;


import com.example.terogmergifx.domain.Prieteni;
import com.example.terogmergifx.domain.StatusPrietenie;
import com.example.terogmergifx.validator.StrategieValidator;
import com.example.terogmergifx.validator.Validator;

import java.sql.*;

public class DBRepoPrieteni extends RepoPrietenie{
    Validator vali = new Validator(StrategieValidator.PRIETENIE);

    private String url;
    private String username;
    private String password;

    public DBRepoPrieteni(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.findAll();
    }

    public void findAll() {

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from \"Prieteni\"");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id1 = resultSet.getString("id1");
                String id2 = resultSet.getString("id2");
                String friendsFrom = resultSet.getString("FriendsFrom");
                String status = resultSet.getString("Status");

                Prieteni prie = new Prieteni(id1,id2, StatusPrietenie.valueOf(status),friendsFrom);
                prieteni.add(prie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPrietenie(Prieteni pr) throws RepoException {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Prieteni\"(\"id1\",\"id2\",\"FriendsFrom\",\"Status\") VALUES (?,?,?,?)");
                statement.setString(1, pr.getId1());
                statement.setString(2, pr.getId2());
                statement.setString(3, pr.getFriendsFrom());
                statement.setString(4, pr.getStatus().toString());
                statement.execute();
                this.prieteni.add(pr);
            } catch (SQLException excep) {
                throw new RepoException("Prietenie deja existenta");
            }
        }



    @Override
    public Prieteni deletePrietenie(Prieteni prien) throws RepoException {
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {    PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Prieteni\" WHERE id1 = (?) and id2 = (?) ");
            statement.setString(1,prien.getId1());
            statement.setString(2,prien.getId2());
            statement.execute();
        } catch (SQLException e) {
            throw new RepoException("Prietenie inexistent");
        }
        this.prieteni.clear();
        this.findAll();
        return  prien;

    }

    @Override
    public void updateFriendShipStatus(String id1, String id2) throws RepoException {
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {    PreparedStatement statement = connection.prepareStatement("UPDATE \"Prieteni\" SET \"Status\"=\'ACTIVE\' WHERE id1 = (?) and id2 = (?) ");
            statement.setString(1,id1);
            statement.setString(2,id2);
            statement.execute();
        } catch (SQLException e) {
            throw new RepoException("Prietenie inexistent");
        }
        this.prieteni.clear();
        this.findAll();
    }

}
