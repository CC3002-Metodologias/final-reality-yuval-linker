package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Knife
 *
 * @author Yuval Linker Groisman
 */
public class Knife extends Weapon {

    public Knife(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.KNIFE);
    }
}
