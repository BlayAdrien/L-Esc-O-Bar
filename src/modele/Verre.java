package modele;

public class Verre {

    public enum  typeVerre{
        CYLINDRE, ENV, ENU
    }

    private typeVerre type;

    public Verre() {
    }

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
