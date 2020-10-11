package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Sword
 *
 * @author Yuval Linker Groisman
 */
public class Sword extends Weapon {
    /**
     * Creates a new sword
     * @param name
     *      The sword's name
     * @param damage
     *      The sword's damage
     * @param weight
     *      The sword's weight
     */
    public Sword(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    /**
     * Test if another object is equal to this instance of a sword
     * @param o
     *    Other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sword)) {
            return false;
        }
        final Sword sword = (Sword) o;
        return getDamage() == sword.getDamage() &&
                getWeight() == sword.getWeight() &&
                getName().equals(sword.getName());
    }

}
