package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Partie {

    private final IntegerProperty score = new SimpleIntegerProperty();

    private int meilleurScore;

    private Commande commande;

    public Partie(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    public void genererCommandes(){
            commande = (new Commande().genererCommande());
    }


    public Commande getCommande() {
        return commande;
    }

    public Integer getScore() {return score.get();}
    public IntegerProperty scoreProperty() {return score;}
    public void setScore(Integer score) {this.score.set(score);}

    public int getMeilleurScore() {
        return meilleurScore;
    }

    public void setMeilleurScore(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "score=" + score +
                ", meilleurScore=" + meilleurScore +
                ", commandes=" + commande +
                '}';
    }
}
