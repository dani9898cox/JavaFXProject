package com.example.terogmergifx.domain;

import java.util.Objects;

public class PrieteniDto {
    private String name;
    private String status;
    private String friendsFrom;

    public PrieteniDto(String name, String status, String friendsFrom) {
        this.name = name;
        this.status = status;
        this.friendsFrom = friendsFrom;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getFriendsFrom() {
        return friendsFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrieteniDto that = (PrieteniDto) o;
        return Objects.equals(name, that.name) && Objects.equals(status, that.status) && Objects.equals(friendsFrom, that.friendsFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, friendsFrom);
    }

    @Override
    public String toString() {
        return "PrieteniDto{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", friendsFrom='" + friendsFrom + '\'' +
                '}';
    }
}
