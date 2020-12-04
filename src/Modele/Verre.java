package Modele;

public class Verre {

    private String forme;

    private Double contenance;

    public Verre(String forme, Double contenance) {
        this.forme = forme;
        this.contenance = contenance;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public Double getContenance() {
        return contenance;
    }

    public void setContenance(Double contenance) {
        this.contenance = contenance;
    }
}
