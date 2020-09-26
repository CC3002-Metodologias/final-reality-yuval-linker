package com.github.ylinker.finalreality.model.weapon;

public class Bow extends Weapon {

    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.BOW);
    }
}
