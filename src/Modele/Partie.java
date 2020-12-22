package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Partie {

    private final IntegerProperty score = new SimpleIntegerProperty();

    private IntegerProperty tpsPartie = new SimpleIntegerProperty();

    private int meilleurScore;

    private boolean enPreparation = false;

    private Commande commande;

    private Commande commandeEnCours = new Commande();

    public Partie(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    public void genererCommandes(){
            commande = (new Commande().genererCommande());
    }

    public boolean isEnPreparation() {
        return enPreparation;
    }

    public void setEnPreparation(boolean enPreparation) {
        this.enPreparation = enPreparation;
    }

    public Commande getCommande() {
        return commande;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public void setCommandeEnCours(Commande commandeEnCours) {
        this.commandeEnCours = commandeEnCours;
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

    public int getTpsPartie() {
        return tpsPartie.get();
    }

    public IntegerProperty tpsPartieProperty() {
        return tpsPartie;
    }

    public void setTpsPartie(int tpsPartie) {
        this.tpsPartie.set(tpsPartie);
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
