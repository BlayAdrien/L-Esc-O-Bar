package Modele;

public abstract class Boisson {

    private boolean prepare = false;

    public boolean isPrepare() {
        return prepare;
    }

    public void setPrepare(boolean prepare) {
        this.prepare = prepare;
    }
}
