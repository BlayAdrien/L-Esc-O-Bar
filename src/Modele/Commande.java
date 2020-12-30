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


    /**
     * Genere un commande avec un nombre et un type de boissons aléatoire
     * @param avecCocktail
     * @return une commande qui contient des boissons aléatoires
     */
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

    /**
     * Verifie si toutes les boissons de la commande sont prêtes
     * @return true si toutes les boissons sont prêtes sinon false
     */
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

    /**
     * Ajoute une boisson à la liste de boissons
     * @param boisson
     */
    public void addBoisson(Boisson boisson){
        this.commande.add(boisson);
    }

    public int getNbBoissonMax() {
        return nbBoissonMax;
    }

    public void setNbBoissonMax(int nbBoissonMax) {
        this.nbBoissonMax = nbBoissonMax;
    }

    /**
     * Renvoie un cocktail contenue dans la commande en cours
     * @return
     */
    public Cocktail searchCocktail(){
        for (Boisson b: commande
             ) {
            if ( b instanceof Cocktail){
                return (Cocktail)b;
            }
        }
        return null;
    }

    /**
     *     Renvoie un cocktail contenue dans la commande en cours en spécifiant son type
      * @param typeCocktail
     * @return
     */
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
