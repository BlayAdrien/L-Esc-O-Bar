package Modele;

import java.util.Objects;

public class Ingredient {

    public enum TypeIngredient{
        CITRON, MENTHE, TEQUILA, RHUM, GLACE
    }

    private TypeIngredient type;

    public Ingredient(TypeIngredient type) {
        this.type = type;
    }

    public TypeIngredient getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return type == that.type;
    }


}
