package Modele;

import java.util.Objects;

public class Biere extends Boisson{



    public enum  choixBiere{
        BLONDE,BRUNE
    }

    private choixBiere typeBiere;

    private Verre verre;

    private static final int valeur = 10;

    private static final String img = "../img/biere_blonde.jpg";

    public Biere(choixBiere typeBiere) {
        this.typeBiere = typeBiere;
    }

    @Override
    public String imgPath() {
        return img;
    }

    @Override
    public String toString() {
        return "Biere{" +
                "typeBiere=" + typeBiere +
                '}';
    }

    @Override
    public int getScore(){
        return valeur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return typeBiere == biere.typeBiere;
    }


}