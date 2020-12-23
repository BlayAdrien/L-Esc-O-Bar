package Modele;

import java.util.LinkedList;
import java.util.List;

public class Cocktail extends Boisson{

    public enum TypeCocktail{
        MARGARITA, MOJITO
    }

    private final  LinkedList<Ingredient> listMojito = new LinkedList<>(List.of(new Ingredient(Ingredient.TypeIngredient.MENTHE), new Ingredient(Ingredient.TypeIngredient.CITRON),new Ingredient(Ingredient.TypeIngredient.GLACE),new Ingredient(Ingredient.TypeIngredient.RHUM)));

    private final  LinkedList<Ingredient> listMargarita = new LinkedList<>(List.of( new Ingredient(Ingredient.TypeIngredient.CITRON),new Ingredient(Ingredient.TypeIngredient.GLACE),new Ingredient(Ingredient.TypeIngredient.TEQUILA)));

    private TypeCocktail typeCocktail;

    private LinkedList<Ingredient> ingredients = new LinkedList<>();

    private static final int valeur = 30;

    private boolean shake=false;

    private Verre verre;

    public Cocktail() {
        super();
    }

    public Cocktail(TypeCocktail typeCocktail) {
        super(typeCocktail.toString());
        this.typeCocktail = typeCocktail;

    }

    public Cocktail(Verre verre) {
        this.verre = verre;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.addLast(ingredient);
    }

    public boolean verifOrdreIngredient(){

            if(ingredients.equals(listMojito) && isShake()){
                this.setTypeCocktail(TypeCocktail.MOJITO);
                return true;
            }

            if(ingredients.equals(listMargarita) && isShake()){
                this.setTypeCocktail(TypeCocktail.MARGARITA);
                return true;
            }
        return false;
    }

    public LinkedList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "nom='" + super.getName() + '\'' +
                '}';
    }

    @Override
    public int getScore(){
        return valeur;
    }


    public TypeCocktail getTypeCocktail() {
        return typeCocktail;
    }

    public void setTypeCocktail(TypeCocktail typeCocktail) {
        this.typeCocktail = typeCocktail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return typeCocktail == cocktail.typeCocktail;
    }

    public boolean isShake() {
        return shake;
    }

    public void setShake(boolean shake) {
        this.shake = shake;
    }

}
