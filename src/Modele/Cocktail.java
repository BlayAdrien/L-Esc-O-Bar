package Modele;

import java.util.ArrayList;

public class Cocktail extends Boisson {

    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    private String nom = "Cocktail";

    @Override
    public String toString() {
        return "Cocktail{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
