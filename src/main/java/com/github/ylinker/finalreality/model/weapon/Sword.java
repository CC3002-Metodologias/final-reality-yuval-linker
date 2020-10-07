package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Sword
 *
 * @author Yuval Linker Groisman
 */
public class Sword extends Weapon {

    public Sword(final String name, final int damage, final int speed, final int weight) {
        super(name, damage, speed, weight);
    }

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
                getSpeed() == sword.getSpeed() &&
                getName().equals(sword.getName());
    }

}
