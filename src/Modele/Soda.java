package Modele;

import java.util.Objects;

public class Soda extends Boisson {

    private String nom = "Soda";

    private static final int valeur = 10;

    public Soda() {
    }

    @Override
    public String toString() {
        return "Soda{" +
                "nom='" + nom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soda soda = (Soda) o;
        return Objects.equals(nom, soda.nom);
    }




}
