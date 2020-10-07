package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Knife
 *
 * @author Yuval Linker Groisman
 */
public class Knife extends Weapon {

    public Knife(final String name, final int damage, final int speed, final int weight) {
        super(name, damage, speed, weight);
    }

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
                getSpeed() == knife.getSpeed() &&
                getName().equals(knife.getName());
    }
}
