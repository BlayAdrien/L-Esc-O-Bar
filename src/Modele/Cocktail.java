package Modele;

import java.util.Arrays;
import java.util.LinkedList;

public class Cocktail extends Boisson{

    public enum TypeCocktail{
        MARGARITA, MOJITO
    }

    private final  LinkedList<Ingredient> listMojito = new LinkedList<>(Arrays.asList(new Ingredient(Ingredient.TypeIngredient.MENTHE), new Ingredient(Ingredient.TypeIngredient.CITRON),new Ingredient(Ingredient.TypeIngredient.GLACE),new Ingredient(Ingredient.TypeIngredient.RHUM)));

    private final  LinkedList<Ingredient> listMargarita = new LinkedList<>(Arrays.asList( new Ingredient(Ingredient.TypeIngredient.CITRON),new Ingredient(Ingredient.TypeIngredient.GLACE),new Ingredient(Ingredient.TypeIngredient.TEQUILA)));

    private TypeCocktail typeCocktail;

    private LinkedList<Ingredient> ingredients = new LinkedList<>();

    public static final int VALEUR = 30;

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

    /**
     * Ajoute un ingrédient à la fin de la liste d'ingrédients
     * @param ingredient
     */
    public void addIngredient(Ingredient ingredient){
        ingredients.addLast(ingredient);
    }

    /**
     * Vérifie si les ingrédients contenue dans la liste d'ingredients sont les bons et dans le bon ordre
     * @return true si c'est le bon ordre sinon false
     */
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
        return VALEUR;
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
