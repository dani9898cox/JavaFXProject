package com.example.terogmergifx.service;


import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.repository.RepoException;
import com.example.terogmergifx.repository.RepoUtilizatori;
import com.example.terogmergifx.utils.Sha1Convertor;
import com.example.terogmergifx.validator.StrategieValidator;
import com.example.terogmergifx.validator.ValidationException;
import com.example.terogmergifx.validator.Validator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUtilizatori {
    Validator vali = new Validator(StrategieValidator.UTILIZATOR);

    Sha1Convertor convertor = new Sha1Convertor();
    RepoUtilizatori repoUtilizator;

    public ServiceUtilizatori(RepoUtilizatori repoUtilizator) {
        this.repoUtilizator = repoUtilizator;

    }

    public void adaugaUtilizator(String id, String nume, String parola, String email) throws RepoException, ValidationException, NoSuchAlgorithmException {

        parola = convertor.sha1(parola);
        Utilizator user = new Utilizator(id, nume, parola, email);
        vali.validare(user);
        this.repoUtilizator.addUser(user);
    }

    public void stergeUtilizato(String id) throws RepoException {
        Utilizator user = this.repoUtilizator.searchUser(id);
        this.repoUtilizator.deleteUser(user);
    }

    public Utilizator checkPassword(String id, String password) throws RepoException, NoSuchAlgorithmException {
        Utilizator user = this.repoUtilizator.searchUser(id);
        if (user.getPassword().equals(convertor.sha1(password)))
            return user;

        else throw new RepoException("Parola Gresita");
    }

    private void initRepo() {
        Utilizator user1 = new Utilizator("1", "1", "1", "1");
        Utilizator user2 = new Utilizator("2", "2", "2", "2");
        Utilizator user3 = new Utilizator("3", "3", "3", "3");
        Utilizator user4 = new Utilizator("4", "4", "4", "4");
        Utilizator user6 = new Utilizator("5", "5", "5", "5");
        Utilizator user7 = new Utilizator("6", "5", "5", "5");
        Utilizator user8 = new Utilizator("7", "5", "5", "5");
        Utilizator user9 = new Utilizator("8", "5", "5", "5");
        Utilizator user10 = new Utilizator("9", "5", "5", "5");

        try {
            this.repoUtilizator.addUser(user1);
            this.repoUtilizator.addUser(user2);
            this.repoUtilizator.addUser(user3);
            this.repoUtilizator.addUser(user4);
            this.repoUtilizator.addUser(user9);
            this.repoUtilizator.addUser(user6);
            this.repoUtilizator.addUser(user7);
            this.repoUtilizator.addUser(user8);
            this.repoUtilizator.addUser(user10);
        } catch (RepoException ex) {
            System.out.println(ex);
        }

    }

    public List<Utilizator> getUsers() {
        return this.repoUtilizator.getAll();
    }

    public List<Utilizator> searchUsersWithString(String smthing) {
        List<Utilizator> returnUsers = new ArrayList<Utilizator>();
        for (Utilizator user : this.repoUtilizator.getAll()) {
            if (user.getName().contains(smthing)) {
                returnUsers.add(user);
            }

        }
        return returnUsers;
    }
}
