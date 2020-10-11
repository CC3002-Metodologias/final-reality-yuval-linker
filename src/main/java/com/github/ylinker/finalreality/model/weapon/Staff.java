package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Staff
 *
 * @author Yuval Linker Groisman
 */
public class Staff extends Weapon {

    private final int magicDamage;

    /**
     * Creates a new staff
     * @param name
     *      The staff's name
     * @param damage
     *      The staff's damage
     * @param weight
     *      The staff's weight
     * @param magicDamage
     *      The staff's magic damage
     */
    public Staff(final String name, final int damage, final int weight,
                 final int magicDamage) {
        super(name, damage, weight);
        this.magicDamage = magicDamage;
    }

    /**
     * Gets the staff's magic damage
     * @return an int with the staff's magic damage
     */
    public int getMagicDamage() { return magicDamage; }

    /**
     * Test if another object is equal to this instance of a staff
     * @param o
     *    Other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff staff = (Staff) o;
        return getDamage() == staff.getDamage() &&
                getWeight() == staff.getWeight() &&
                getMagicDamage() == staff.getMagicDamage() &&
                getName().equals(staff.getName());
    }
}
