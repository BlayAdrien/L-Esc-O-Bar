package Modele;

import java.util.ArrayList;

public class Commande{

    private Double patience = 30.00;

    private int nbBoissonMax = 4;

    private ArrayList<Boisson> commande = new ArrayList<>();

    public Commande() {
    }

    public Commande(Double patience) {
        this.patience = patience;
    }

    public Commande(Double patience, ArrayList<Boisson> commande) {
        this.patience = patience;
        this.commande = commande;
    }

    public Double getPatience() {
        return patience;
    }

    public ArrayList<Boisson> getCommande() {
        return commande;
    }

    public Commande genererCommande(boolean avecCocktail) {
        int choixBoisson = 3;
        if (avecCocktail){
             choixBoisson = 5;
         }
        int nbBoissons = (int) (Math.random()*(nbBoissonMax)) + 1;
        for (int i = -5; i < nbBoissons; i++) {
            int boisson = (int) (Math.random()*(choixBoisson)) + 1;
            switch (boisson) {
                case 1:
                    this.addBoisson(new Biere(Biere.choixBiere.BLONDE));
                    break;
                case 2:
                    this.addBoisson(new Biere(Biere.choixBiere.BRUNE));
                    break;
                case 3:
                    this.addBoisson(new Soda());
                    break;
                case 4:
                    this.addBoisson(new Cocktail(Cocktail.TypeCocktail.MOJITO));
                    break;
                case 5:
                    this.addBoisson(new Cocktail(Cocktail.TypeCocktail.MARGARITA));
                    break;
            }
        }

        return this;
    }

    public boolean verifCommande(){
        boolean prete = true;
        for (Boisson boisson : commande
             ) {
            if(!boisson.isPrepare()){
                prete = false;
                break;
            }
        }
        return prete;
    }


    public void addBoisson(Boisson boisson){
        this.commande.add(boisson);
    }

    public int getNbBoissonMax() {
        return nbBoissonMax;
    }

    public void setNbBoissonMax(int nbBoissonMax) {
        this.nbBoissonMax = nbBoissonMax;
    }

    public Cocktail searchCocktail(){
        for (Boisson b: commande
             ) {
            if ( b instanceof Cocktail){
                return (Cocktail)b;
            }
        }
        return null;
    }

    public Cocktail searchCocktail(Cocktail.TypeCocktail typeCocktail){
        for (Boisson b: commande
        ) {
            if ( b.equals(new Cocktail(typeCocktail))){
                return (Cocktail)b;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Commande{" +
               "commande=" + commande +
                '}';
    }
}
