package Modele;

import java.util.ArrayList;

public class Client extends Personne{

    private Double patience;

    private ArrayList<Boisson> commande = new ArrayList<>();

    public Client(Double patience) {
        this.patience = patience;
    }

    public Client(String nom, String prenom, Double patience, ArrayList<Boisson> commande) {
        super(nom, prenom);
        this.patience = patience;
        this.commande = commande;
    }

    public Double getPatience() {
        return patience;
    }

    public ArrayList<Boisson> getCommande() {
        return commande;
    }
}
