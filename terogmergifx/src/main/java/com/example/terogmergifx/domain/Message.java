package com.example.terogmergifx.domain;

import java.util.Objects;

public class Message {
    private String idReciver;
    private String idSender;
    private String message;

    private String time;

    public Message(String idReciver, String idSender, String message, String time) {
        this.idReciver = idReciver;
        this.idSender = idSender;
        this.message = message;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getIdReciver() {
        return idReciver;
    }

    public String getIdSender() {
        return idSender;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return idReciver.equals(message1.idReciver) && idSender.equals(message1.idSender) && Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReciver, idSender, message);
    }

    @Override
    public String toString() {
        return idSender + " : " +
                message + '\t' +
                "\t" + time +
                '\n';
    }
}
