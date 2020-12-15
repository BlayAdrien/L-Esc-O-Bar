package Modele;

        import java.util.ArrayList;
        import java.util.Objects;

public class Cocktail extends Boisson implements VerreInterface{

    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    private static final int valeur = 30;

    private String nom = "Cocktail";

    private Verre verre;

    private static final String img = "img/mojito.jpg";

    public Cocktail() {
    }

    public Cocktail(Verre verre) {
        this.verre = verre;
    }

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

    @Override
    public boolean verifVerre() {
        return ( verre.getType() == Verre.typeVerre.ENV);
    }
}