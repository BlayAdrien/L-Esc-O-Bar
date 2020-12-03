package Modele;

public class Biere extends Boisson{

    public enum  choixBiere{
        BLONDE,BRUNE
    }

    private choixBiere typeBiere;

    private Verre verre;

    public Biere(choixBiere typeBiere) {
        this.typeBiere = typeBiere;
    }

    @Override
    public String toString() {
        return "Biere{" +
                "typeBiere=" + typeBiere +
                '}';
    }
}
