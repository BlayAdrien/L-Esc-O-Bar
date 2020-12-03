package Modele;

import java.util.ArrayList;

public class Partie {

    private int score = 0;

    private int meilleurScore;

    private ArrayList<Commande> commandes = new ArrayList<>();

    public Partie(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    public void genererCommandes(){
        for (int i = 0; i < 6; i++) {
            commandes.add(new Commande().genererCommande());
        }
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
                ", commandes=" + commandes +
                '}';
    }
}
