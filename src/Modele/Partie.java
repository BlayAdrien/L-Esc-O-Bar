package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Partie implements Externalizable {

    private final IntegerProperty score = new SimpleIntegerProperty();

    private final IntegerProperty meilleurScore = new SimpleIntegerProperty();

    private IntegerProperty tpsPartie = new SimpleIntegerProperty();

    private int niveau;

    private boolean enPreparation = false;

    private Commande commande;

    private Commande commandeEnCours = new Commande();

    public Partie() {
    }

    public Partie(int tpsPartie, int niveau) {
        this.setTpsPartie(tpsPartie);
        this.niveau = niveau;
    }

    public void genererCommandes(){
        if (niveau > 2){
            commande = (new Commande().genererCommande(true));
        }
        else{
            commande = (new Commande().genererCommande(false));

        }
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
    public void setScore(Integer score) {
        this.score.set(score);
        if (getScore()>getMeilleurScore()){
            setMeilleurScore(getScore());
        }
    }

    public Integer getMeilleurScore() {return meilleurScore.get();}
    public IntegerProperty meilleurScoreProperty() {return meilleurScore;}
    public void setMeilleurScore(Integer score) {this.meilleurScore.set(score);}

    public int getTpsPartie() {
        return tpsPartie.get();
    }

    public IntegerProperty tpsPartieProperty() {
        return tpsPartie;
    }

    public void setTpsPartie(int tpsPartie) {
        this.tpsPartie.set(tpsPartie);
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "score=" + score +
                ", meilleurScore=" + meilleurScore +
                ", commandes=" + commande +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(meilleurScore.get());
        objectOutput.writeInt(niveau);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        meilleurScore.set(objectInput.readInt());
        setNiveau(objectInput.readInt());
    }
}
