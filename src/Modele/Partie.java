package Modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

public class Partie {

    private final IntegerProperty score = new SimpleIntegerProperty();

    private IntegerProperty tpsPartie = new SimpleIntegerProperty(60);

    private int meilleurScore;

    private Commande commande;

    private Commande commandeEnCours = new Commande();

    public Partie(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    public void genererCommandes(){
            commande = (new Commande().genererCommande());
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
