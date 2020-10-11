package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Axe
 *
 * @author Yuval Linker Groisman
 */
public class Axe extends Weapon {
    /**
     * Creates a new axe
     * @param name
     *      The axe's name
     * @param damage
     *      The axe's damage
     * @param weight
     *      The axe's weight
     */
    public Axe(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    /**
     * Test if another object is equal to this instance of an axe
     * @param o
     *    Other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe axe = (Axe) o;
        return getDamage() == axe.getDamage() &&
                getWeight() == axe.getWeight() &&
                getName().equals(axe.getName());
    }
}
