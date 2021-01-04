package modele;

public class Biere extends Boisson implements IVerre{


    public enum  choixBiere{
        BLONDE,BRUNE
    }

    private choixBiere typeBiere;

    private Verre verre = new Verre();

    private static final int VALEUR = 20;

    public Biere() {
    }

    public Biere(choixBiere typeBiere) {
        super(typeBiere.toString());
        this.typeBiere = typeBiere;
    }

    public Biere(Verre verre) {
        this.verre = verre;
    }

    /**
     * setters du type de biere
     * @param typeBiere
     */
    public void setTypeBiere(choixBiere typeBiere) {
        this.typeBiere = typeBiere;
        super.setName(typeBiere.toString());
    }

    /**
     * Vérifie que ce soit le bon verre
     * @return
     */
    @Override
    public boolean verifVerre() {
        return (this.verre.getType() == Verre.typeVerre.CYLINDRE);
    }

    @Override
    public String toString() {
        return "Biere{" +
                "typeBiere=" + typeBiere +
                '}';
    }

    @Override
    public int getScore(){
        return VALEUR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return typeBiere == biere.typeBiere;
    }


}