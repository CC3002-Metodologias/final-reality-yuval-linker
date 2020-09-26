package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Axe
 *
 * @author Yuval Linker Groisman
 */
public class Axe extends Weapon {

    public Axe(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.AXE);
    }
}
