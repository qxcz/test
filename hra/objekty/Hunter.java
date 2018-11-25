package hra.objekty;

import hra.objekty.utils.Utils;

public class Hunter extends Hero{

    private Weapon weapon;
    public Hunter(int life, int strenght, int speed) {
        super(life, strenght, speed);
        this.weapon = new Bow(100,50);
    }

    public void attack(Hero hero){
        int weaponHit =  Utils.calculateHit(weapon.getStrenght(),weapon.getCritical());
        super.attack(hero,weaponHit);
    }
}
