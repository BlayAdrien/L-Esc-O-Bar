package Modele;

import java.util.ArrayList;
import java.util.Objects;

public class Cocktail extends Boisson {

    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    private static final int valeur = 30;

    private String nom = "Cocktail";

    private static final String img = "img/mojito.jpg";

    @Override
    public String imgPath() {
        return img;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
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
        Cocktail cocktail = (Cocktail) o;
        return Objects.equals(ingredients, cocktail.ingredients) &&
                Objects.equals(nom, cocktail.nom);
    }

}
