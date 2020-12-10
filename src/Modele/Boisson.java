package Modele;

public abstract class Boisson {

    private boolean prepare = false;



    public boolean isPrepare() {
        return prepare;
    }

    public void setPrepare(boolean prepare) {
        this.prepare = prepare;
    }

    public abstract int getScore();

    public abstract boolean equals(Object o);

    public abstract String imgPath();
}
