package com.example.terogmergifx.validator;

import com.example.terogmergifx.domain.Prieteni;
import com.example.terogmergifx.domain.Utilizator;

public class Validator {
    StrategieValidator strategie;

    public Validator(StrategieValidator strategie) {
        this.strategie = strategie;
    }

    /**
     * Functie care valideaza un obiect primit dupa o anumita strategie
     * @param o - obiectul de validat
     * @throws ValidationException - in cazul in care obiectul nu e valid
     */
    public void validare(Object o) throws ValidationException {
        if (this.strategie == StrategieValidator.UTILIZATOR)
            validareUtilizator((Utilizator) o);
        else validarePrieteni((Prieteni) o);

    }

    /**
     * Functie care valideaza un utilizator
     * @param user - utilizatorul care trebuie validat
     * @throws ValidationException - in cazul in care id ul, parola, numele sau emailul sunt vide
     */
    private void validareUtilizator(Utilizator user) throws ValidationException {
        String msg = "";
        if (user.getId().equals("")) {
            msg = msg + "Id invalid !\n";
        }
        if (user.getEmail().equals("") || !user.getEmail().contains("@gmail.com")) msg = msg + "Email invalid\n!";
        if (user.getPassword().equals("") || user.getPassword().length()<5) msg = msg + "Parola invalida !\n";
        if (user.getName().equals("")) msg = msg + "Nume invalid!\n";

        if(msg.length()>0)
            throw new ValidationException(msg);

    }

    /**
     * Functie care valideaza o prietenie
     * @param prien - prietenia care trebuie validata
     * @throws ValidationException in cazul in care id1 sau id2 sunt vide
     */
    private void validarePrieteni(Prieteni prien) throws ValidationException {

        if(prien.getId2().equals("")) throw new ValidationException("Nu este un id vlaid\n");

    }
}
