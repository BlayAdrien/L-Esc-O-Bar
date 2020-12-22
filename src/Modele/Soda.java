package Modele;

import java.util.Objects;

public class Soda extends Boisson {

    private static final int valeur = 10;

    public Soda() {
        super("Soda");
    }


    @Override
    public String toString() {
        return "Soda{" +
                "nom='" + super.getName() + '\'' +
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
        return Objects.equals(super.getName(), soda.getName());
    }




}
