package com.example.terogmergifx.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Prieteni {
    String id1;
    String id2;
    StatusPrietenie status;

    String friendsFrom;

    public Prieteni(String id1, String id2, LocalDateTime friendsFrom, StatusPrietenie status) {
        this.id1 = id1;
        this.id2 = id2;
        this.status = status;
        this.friendsFrom = friendsFrom.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    }

    public Prieteni(String id1, String id2, StatusPrietenie status, String friendsFrom) {
        this.id1 = id1;
        this.id2 = id2;
        this.status = status;
        this.friendsFrom = friendsFrom;
    }

    public String getId1() {
        return id1;
    }

    public String getId2() {
        return id2;
    }

    public StatusPrietenie getStatus() {
        return status;
    }

    public void setStatus(StatusPrietenie status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prieteni prieteni = (Prieteni) o;
        return Objects.equals(id1, prieteni.id1) && Objects.equals(id2, prieteni.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2, status);
    }

    @Override
    public String toString() {
        return "Prieteni{" +
                "id1='" + id1 + '\'' +
                ", id2='" + id2 + '\'' +
                ", friends since : " + friendsFrom + '\'' +
                ", status=" + status +
                '}';
    }

    public String getFriendsFrom() {
        return friendsFrom;
    }
}
