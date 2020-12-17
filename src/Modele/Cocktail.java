package Modele;

        import java.sql.SQLOutput;
        import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.Objects;

public class Cocktail extends Boisson implements VerreInterface{

    public enum TypeCocktail{
        MARGARITA, MOJITO
    }



    private TypeCocktail typeCocktail;

    private LinkedList<Ingredient> ingredients = new LinkedList<>();

    private static final int valeur = 30;

    private Verre verre;

    private static final String img = "img/mojito.jpg";

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
        ingredients.add(ingredient);
    }

    public boolean verifOrdreIngredient(Ingredient ingredient){

        if (this.getTypeCocktail() == TypeCocktail.MOJITO){
            switch (ingredient.getType()){
                case GLACE:
                    if(this.ingredients.getLast().getType() == Ingredient.TypeIngredient.CITRON){
                        this.addIngredient(ingredient);
                        return true;
                    }
                    else{
                        System.out.println("Pas le bon ingredient");
                        return false;
                    }

                case CITRON:
                    if(this.ingredients.getLast().getType() == Ingredient.TypeIngredient.MENTHE){
                        this.addIngredient(ingredient);
                        return true;
                    }
                    else{
                        System.out.println("Pas le bon ingredient");
                        return false;
                    }
                case RHUM:
                    if(this.ingredients.getLast().getType() == Ingredient.TypeIngredient.GLACE){
                        this.addIngredient(ingredient);
                        return true;
                    }
                    else{
                        System.out.println("Pas le bon ingredient");
                        return false;
                    }
            }
        }
        if (this.getTypeCocktail() == TypeCocktail.MARGARITA){
            switch (ingredient.getType()){
                case GLACE:
                    if(this.ingredients.getLast().getType() == Ingredient.TypeIngredient.CITRON){
                        this.addIngredient(ingredient);
                        return true;
                    }
                    else{
                        System.out.println("Pas le bon ingredient");
                        return false;
                    }

                case TEQUILA:
                    if(this.ingredients.getLast().getType() == Ingredient.TypeIngredient.GLACE){
                        this.addIngredient(ingredient);
                        return true;
                    }
                    else{
                        System.out.println("Pas le bon ingredient");
                        return false;
                    }
            }
        }
        return false;
    }

    public LinkedList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String imgPath() {
        return img;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return typeCocktail == cocktail.typeCocktail;
    }



    @Override
    public boolean verifVerre() {
        return ( verre.getType() == Verre.typeVerre.ENV);
    }
}
