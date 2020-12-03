package Modele;

import java.util.ArrayList;

public class Partie {

    private int score = 0;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

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
