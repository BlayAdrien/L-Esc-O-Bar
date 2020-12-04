package Modele;

public class Niveau {

    public enum  choixNiveau{
        UN,DEUX,TROIS
    }

    private choixNiveau leNiveau;

    private Double tempsPartie;

    public Niveau(Double tempsPartie) {
        this.tempsPartie = tempsPartie;
    }

    public Double getTempsPartie() {
        return tempsPartie;
    }

    public void setTempsPartie(Double tempsPartie) {
        this.tempsPartie = tempsPartie;
    }
}
