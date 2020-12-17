package Modele;

public abstract class Boisson {

    private boolean prepare = false;

    private String name;

    public Boisson() {
    }

    public Boisson(String name) {
        this.name = name;
    }

    public boolean isPrepare() {
        return prepare;
    }

    public void setPrepare(boolean prepare) {
        this.prepare = prepare;
    }

    public abstract int getScore();

    public void setName(String name) {
        this.name = name;
    }

    public  String getName(){
        return name;
    }

    public abstract boolean equals(Object o);

    public abstract String imgPath();
}
