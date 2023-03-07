package com.example.terogmergifx.repository;

import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.validator.StrategieValidator;
import com.example.terogmergifx.validator.ValidationException;
import com.example.terogmergifx.validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRepoUtilizatori extends RepoUtilizatori {

    String filepath;
    Validator vali = new Validator(StrategieValidator.UTILIZATOR);

    public FileRepoUtilizatori(String filepath) {
        this.filepath = filepath;
    }

    public void readFromFile() throws FileNotFoundException, RepoException, ValidationException {

        File fisier = new File(this.filepath);
        Scanner reader = new Scanner(fisier);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] sir = data.split(";", 4);
            if (sir.length == 4) {
                Utilizator usr = new Utilizator(sir[0], sir[1], sir[2], sir[3]);
                vali.validare(usr);
                this.users.add(usr);
            }
        }
    }


    public void saveToFile() throws IOException {
        FileWriter scriitor = new FileWriter(this.filepath);
        for (Utilizator e : this.users) {
            String date = e.getId() + ";" + e.getName() + ";" + e.getPassword() + ";" + e.getEmail() + "\n";
            scriitor.write(date);
        }
        scriitor.close();

    }

}

