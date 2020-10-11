package com.github.ylinker.finalreality.model.weapon;

public interface IWeapon {
    /**
     * Returns this weapon's name
     */
    public String getName();

    /**
     * Returns this weapon's damage
     */
    public int getDamage();

    /**
     * Returns this weapon's weight
     */
    public int getWeight();

    /**
     * Returns wether another object is equal to this instance of a weapon
     */
    public boolean equals(final Object o);

    /**
     * Returns this unique hashcode
     */
    public int hashCode();
}
