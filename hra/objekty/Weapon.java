package hra.objekty;

public abstract class Weapon {

    private int strenght;
    private int critical;

    public Weapon(int strenght, int speed) {
        this.strenght = strenght;
        this.critical = speed;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getCritical() {
        return critical;
    }
}
