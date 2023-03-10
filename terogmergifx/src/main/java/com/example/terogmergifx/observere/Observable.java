package com.example.terogmergifx.observere;

public interface Observable<E> {
    void addObserver(Observer<E> e);

    void removeObserver(Observer<E> e);

    void notifyObservers(E t);
}
