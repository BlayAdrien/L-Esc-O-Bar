package Modele;

public class Ingredient {

    public enum TypeIngredient{
        CITRON, MENTHE, TEQUILA, RHUM, GLACE
    }

    private TypeIngredient type;

    public Ingredient(TypeIngredient type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "nom='" + type + '\'' +
                '}';
    }
}
