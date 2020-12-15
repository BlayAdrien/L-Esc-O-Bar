package Modele;

public class Verre {

    public enum  typeVerre{
        CYLINDRE, ENV
    }

    private typeVerre type;


    public Verre(typeVerre type) {
        this.type = type;
    }

    public typeVerre getType() {
        return type;
    }

    public void setType(typeVerre type) {
        this.type = type;
    }
}
