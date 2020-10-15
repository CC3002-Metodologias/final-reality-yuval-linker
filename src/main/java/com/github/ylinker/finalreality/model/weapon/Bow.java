package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Bow
 *
 * @author Yuval Linker Groisman
 */
public class Bow extends Weapon {
    /**
     * Creates a new bow
     * @param name
     *      The bow's name
     * @param damage
     *      The bow's damage
     * @param weight
     *      The bow's weight
     */
    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    /**
     * Test if another object is equal to this instance of a bow
     * @param o
     *    Other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow bow = (Bow) o;
        return getDamage() == bow.getDamage() &&
                getWeight() == bow.getWeight() &&
                getName().equals(bow.getName());
    }

    @Override
    public IWeapon equipToEngineer() {
        return this;
    }

    @Override
    public IWeapon equipToThief() {
        return this;
    }
}
