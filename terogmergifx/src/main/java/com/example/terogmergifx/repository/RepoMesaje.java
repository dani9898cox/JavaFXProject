package com.example.terogmergifx.repository;

import com.example.terogmergifx.domain.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RepoMesaje {

    private String filepath;

    private String text;

    public RepoMesaje(String filepath) {
        this.filepath = filepath;
    }

    public void sendMessage(Message msg) {
        text = text + msg;
    }

    public void saveToFile() throws IOException {
        FileWriter scriitor = new FileWriter(this.filepath);
        scriitor.write(text);
        scriitor.close();
    }

    public void readFromFile() throws IOException {

        text = Files.readString(Path.of(filepath));

    }

    public String getText() {
        return text;
    }

}
