package com.example.terogmergifx.repository;


import com.example.terogmergifx.domain.Prieteni;
import com.example.terogmergifx.domain.StatusPrietenie;

import java.util.ArrayList;
import java.util.List;

public class RepoPrietenie {

    List<Prieteni> prieteni = new ArrayList<Prieteni>();

    /**
     * Functie care adauga o prietenie in com.example.proiectualamarefxml.repository
     *
     * @param pr - relatia de prietenie ce urmeaza sa fie adaugat
     * @throws RepoException - daca relatia exista deja
     */
    public void addPrietenie(Prieteni pr) throws RepoException {
        try {
            searchPrietenie(pr.getId1(), pr.getId2());
            throw new RepoException("Prietenie existent");
        } catch (RepoException e) {
            this.prieteni.add(pr);
        }
    }

    /**
     * functie care sterge o relatie de prietenie din com.example.proiectualamarefxml.repository
     *
     * @param prien - prietenia care trebuie stearsa
     * @return intoarce relatia stearsa
     * @throws RepoException - daca relatia nu exista
     */
    public Prieteni deletePrietenie(Prieteni prien) throws RepoException {

        int i = 0;
        for (Prieteni pr : this.prieteni) {
            if (pr.equals(prien)) {
                return this.prieteni.remove(i);
            } else i++;
        }
        throw new RepoException("Prietenie inexistent");
    }

    /**
     * Functie care cauta o prietenie in repo dupa id1 si id2
     *
     * @param id1 - String id ul unei persoane
     * @param id2 _ String idul celeilalte persoane
     * @return Prietenai daca a fost gasita, arunca exceptie altfel
     * @throws RepoException - in cazul in care prietenia nu a fost gasita
     */
    public Prieteni searchPrietenie(String id1, String id2) throws RepoException {
        for (Prieteni pr : this.prieteni) {
            if (pr.getId1().equals(id1) && pr.getId2().equals(id2))
                return pr;
        }
        throw new RepoException("Utilizator inexistent");
    }


    public Integer repoSize() {
        return this.prieteni.size();
    }

    public List<Prieteni> getAll() {
        return this.prieteni;
    }

    public void updateFriendShipStatus(String id1, String id2) throws RepoException {
        for (Prieteni pr : this.prieteni) {
            if (pr.getId1().equals(id1) && pr.getId2().equals(id2)) {
                pr.setStatus(StatusPrietenie.ACTIVE);
                break;
            }
        }
        throw new RepoException("prietenie inexistenta");

    }
}
