package Modele;

public class Partie {

    private int score = 0;

    private int meilleurScore;

    private Niveau niveau;

    public Partie(int meilleurScore, Niveau niveau) {
        this.meilleurScore = meilleurScore;
        this.niveau = niveau;
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

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
