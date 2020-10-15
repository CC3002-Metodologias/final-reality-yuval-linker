package com.github.ylinker.finalreality.model.weapon;

public interface IWeapon {
    /**
     * Returns this weapon's name
     */
    String getName();

    /**
     * Returns this weapon's damage
     */
    int getDamage();

    /**
     * Returns this weapon's weight
     */
    int getWeight();
    /**
     * Returns wether another object is equal to this instance of a weapon
     */
    boolean equals(final Object o);

    /**
     * Returns this unique hashcode
     */
    int hashCode();

    /**
     * Equips this weapon to an Engineer
     */
    IWeapon equipToEngineer();

    /**
     * Equips this weapon to a Knight
     */
    IWeapon equipToKnight();

    /**
     * Equips this weapon to a Thief
     */
    IWeapon equipToThief();

    /**
     * Equips this weapon to a Black Mage
     */
    IWeapon equipToBlackMage();

    /**
     * Equips this weapon to a White Mage
     */
    IWeapon equipToWhiteMage();
}
