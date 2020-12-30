package Modele;

public class Biere extends Boisson {


    public enum  choixBiere{
        BLONDE,BRUNE
    }

    private choixBiere typeBiere;

    private Verre verre;

    public static final int VALEUR = 20;

    public Biere(choixBiere typeBiere) {
        super(typeBiere.toString());
        this.typeBiere = typeBiere;
    }

    public Biere(Verre verre) {
        this.verre = verre;
    }

    public void setTypeBiere(choixBiere typeBiere) {
        this.typeBiere = typeBiere;
        super.setName(typeBiere.toString());
    }


    @Override
    public String toString() {
        return "Biere{" +
                "typeBiere=" + typeBiere +
                '}';
    }

    @Override
    public int getScore(){
        return VALEUR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return typeBiere == biere.typeBiere;
    }


}