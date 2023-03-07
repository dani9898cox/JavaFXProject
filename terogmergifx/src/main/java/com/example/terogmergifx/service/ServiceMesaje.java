package com.example.terogmergifx.service;

import com.example.terogmergifx.domain.Message;
import com.example.terogmergifx.repository.RepoMesaje;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceMesaje {
    private final RepoMesaje repoMsg;

    public ServiceMesaje(RepoMesaje repoMsg) {
        this.repoMsg = repoMsg;
    }

    public void addMessage(String idSender, String idReciver, String msg) throws IOException {
        LocalTime dateTime = LocalDateTime.now().toLocalTime();
        String time = dateTime.toString().substring(0,5);
        Message message = new Message(idSender,idReciver,msg,time);
        this.repoMsg.sendMessage(message);
        this.repoMsg.saveToFile();
    }

    public String getMessages() throws IOException {
        this.repoMsg.readFromFile();
        return this.repoMsg.getText();
    }


}
