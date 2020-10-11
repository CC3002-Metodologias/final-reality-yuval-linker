package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Knife
 *
 * @author Yuval Linker Groisman
 */
public class Knife extends Weapon {
    /**
     * Creates a new knife
     * @param name
     *      The knife's name
     * @param damage
     *      The knife's damage
     * @param weight
     *      The knife's weight
     */
    public Knife(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    /**
     * Test if another object is equal to this instance of a knife
     * @param o
     *    Other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife knife = (Knife) o;
        return getDamage() == knife.getDamage() &&
                getWeight() == knife.getWeight() &&
                getName().equals(knife.getName());
    }
}
