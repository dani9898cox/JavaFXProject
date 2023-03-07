package com.example.terogmergifx.service;


import com.example.terogmergifx.domain.Prieteni;
import com.example.terogmergifx.domain.StatusPrietenie;
import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.repository.RepoException;
import com.example.terogmergifx.repository.RepoPrietenie;
import com.example.terogmergifx.validator.StrategieValidator;
import com.example.terogmergifx.validator.ValidationException;
import com.example.terogmergifx.validator.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicePrieteni {
    Validator vali = new Validator(StrategieValidator.PRIETENIE);
    RepoPrietenie repoPrieteni;

    public ServicePrieteni(RepoPrietenie repoPrieteni) {
        this.repoPrieteni = repoPrieteni;
    }

    public void adaugaPrietenie(String id1, String id2) throws RepoException, ValidationException {
        LocalDateTime dateTime = LocalDateTime.now();
        Prieteni pr = new Prieteni(id1, id2, dateTime, StatusPrietenie.REQUESTED);
        vali.validare(pr);
        repoPrieteni.addPrietenie(pr);
    }

    public void stergePrietenie(String id1, String id2) throws RepoException {

        repoPrieteni.deletePrietenie(new Prieteni(id1, id2, LocalDateTime.now(), StatusPrietenie.REQUESTED));
    }

    public void acceptaPrietenie(String id1, String id2) throws RepoException {

        this.repoPrieteni.updateFriendShipStatus(id1,id2);
    }

    public List<Prieteni> getPrieteni() {
        return this.repoPrieteni.getAll();
    }

    public List<Prieteni> prieteniUtilizator(Utilizator user)
    {
        List<Prieteni> requests = new ArrayList<Prieteni>();

        for (Prieteni pr : this.repoPrieteni.getAll())
        {
            if((pr.getId2().equals(user.getId()) || pr.getId1().equals(user.getId())) && pr.getStatus().equals(StatusPrietenie.ACTIVE))
                requests.add(pr);
        }
        return requests;
    }

    public List<Prieteni> cereriTrimise(Utilizator user)
    {
        List<Prieteni> cereri = new ArrayList<Prieteni>();

        for (Prieteni pr: this.repoPrieteni.getAll())
        {
            if (Objects.equals(pr.getId1(), user.getId()) && pr.getStatus().equals(StatusPrietenie.REQUESTED))
                cereri.add(pr);
        }
        return cereri;
    }


    public List<Prieteni> cereriPrimite(Utilizator user)
    {
        List<Prieteni> cereri = new ArrayList<Prieteni>();

        for (Prieteni pr: this.repoPrieteni.getAll())
        {
            if (Objects.equals(pr.getId2(), user.getId()) && pr.getStatus().equals(StatusPrietenie.REQUESTED))
                cereri.add(pr);
        }
        return cereri;
    }
}
