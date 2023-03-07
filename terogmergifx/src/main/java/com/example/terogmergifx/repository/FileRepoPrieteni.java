package com.example.terogmergifx.repository;

import com.example.terogmergifx.domain.Prieteni;
import com.example.terogmergifx.domain.StatusPrietenie;
import com.example.terogmergifx.validator.StrategieValidator;
import com.example.terogmergifx.validator.ValidationException;
import com.example.terogmergifx.validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRepoPrieteni extends RepoPrietenie {

    String filepath;
    Validator vali = new Validator(StrategieValidator.PRIETENIE);

    public FileRepoPrieteni(String filepath) {
        this.filepath = filepath;
    }

    public void readFromFile() throws FileNotFoundException, RepoException, ValidationException {

        File fisier = new File(this.filepath);
        Scanner reader = new Scanner(fisier);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] sir = data.split(";", 4);
            if(sir.length==4) {
                Prieteni pr = new Prieteni(sir[0], sir[1], StatusPrietenie.valueOf(sir[2]), sir[3]);
                vali.validare(pr);
                this.prieteni.add(pr);
            }
        }
    }


    public void saveToFile() throws IOException {
        FileWriter scriitor = new FileWriter(this.filepath);
        for (Prieteni e : this.prieteni) {
            String date = e.getId1() + ";" + e.getId2() + ";" + e.getStatus() + ";" + e.getFriendsFrom() + ";\n";
            scriitor.write(date);
        }
        scriitor.close();

    }
}
