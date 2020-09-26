package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Bow
 *
 * @author Yuval Linker Groisman
 */
public class Bow extends Weapon {

    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.BOW);
    }
}
