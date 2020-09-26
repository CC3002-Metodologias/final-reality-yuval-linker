package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Sword
 *
 * @author Yuval Linker Groisman
 */
public class Sword extends Weapon {

    public Sword(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.SWORD);
    }
}
