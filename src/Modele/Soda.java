package Modele;

import java.util.Objects;

public class Soda extends Boisson {

    private String nom = "Soda";

    private static final int valeur = 10;

    private static final String img = "img/soda.jpg";


    public Soda() {
    }

    @Override
    public String imgPath() {
        return img;
    }

    @Override
    public String toString() {
        return "Soda{" +
                "nom='" + nom + '\'' +
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
        Soda soda = (Soda) o;
        return Objects.equals(nom, soda.nom);
    }




}
