package hra.objekty;

import hra.objekty.utils.Utils;

public abstract class Hero {

    private int life;
    private int strenght;
    private int speed;
    private boolean isAttackable;
    private int defCount;

    public Hero(int life, int strenght, int speed) {
        this.life = life;
        this.strenght = strenght;
        this.speed = speed;
        this.isAttackable=true;
        this.defCount=0;
    }

    protected void attack(Hero hero, int weaponHit){
        isAttackable = true;
        if (hero.isAttackable) {
            int hit = Utils.calculateHit(strenght,speed) + weaponHit;
            hero.setLife(hero.getLife() - hit);
        }
    }

    protected boolean defend(){
        if (defCount<=2) {
            isAttackable = false;
            defCount++;
            return true;
        }
        return false;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getSpeed() {
        return speed;
    }
}
