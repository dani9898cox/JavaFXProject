package com.example.terogmergifx.repository;


import com.example.terogmergifx.domain.Utilizator;

import java.util.ArrayList;
import java.util.List;

public class RepoUtilizatori {
    List<Utilizator> users = new ArrayList<Utilizator>();

    /**
     * Functie care adauga un utilizator in com.example.proiectualamarefxml.repository
     *
     * @param user - utilizatorul ce urmeaza sa fie adaugat
     * @throws RepoException - daca utilizatorul exista deja
     */
    public void addUser(Utilizator user) throws RepoException {
        try {
            searchUser(user.getId());
            throw new RepoException("Utilizator existent");
        } catch (RepoException e) {
            this.users.add(user);
        }
    }

    /**
     * functie care sterge un utilizator din com.example.proiectualamarefxml.repository
     *
     * @param user - utilizatorul care trebuie ster
     * @return intoarce utilizatorul sters nu are nici un sens
     * @throws RepoException - daca utilizatorul nu exista
     */
    public Utilizator deleteUser(Utilizator user) throws RepoException {

        int i = 0;
        for (Utilizator u : this.users) {
            if (u.equals(user)) {
                return this.users.remove(i);
            } else i++;
        }
        throw new RepoException("Utilizator inexistent");
    }

    /**
     * Functie care primeste un id de utilizator si il cauta in repo,
     * returneaza utilizatorul sa u arunca exceptie daca nu a fost gasit
     * @param id - String, idul utilizatroului cautat
     * @return Utilizator, utilizatorul cautat
     * @throws RepoException , in caazul in care nu exista un utilziator cu acel id
     */
    public Utilizator searchUser(String id) throws RepoException {
        for (Utilizator u : this.users) {
            if (u.getId().equals(id))
                return u;
        }
        throw new RepoException("Utilizator inexistent");
    }

    /**
     * Funcite care returneaza cate elemente sunt in com.example.proiectualamarefxml.repository
     * @return Integer, nr de utilizatori
     */
    public Integer repoSize() {
        return this.users.size();
    }

    /**
     * Functie care returneaza o lista cu utilizatori
     * @return List, lista cu toti Utilizatori
     */
    public List<Utilizator> getAll() {
        return this.users;
    }
}
