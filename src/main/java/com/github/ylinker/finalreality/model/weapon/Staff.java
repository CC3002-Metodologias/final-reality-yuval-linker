package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Staff
 *
 * @author Yuval Linker Groisman
 */
public class Staff extends Weapon {

    private final int magicDamage;

    public Staff(final String name, final int damage, final int speed, final int weight,
                 final int magicDamage) {
        super(name, damage, speed, weight);
        this.magicDamage = magicDamage;
    }

    public int getMagicDamage() { return magicDamage; }

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
                getName().equals(staff.getName());
    }
}
