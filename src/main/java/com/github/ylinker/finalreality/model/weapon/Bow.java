package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Bow
 *
 * @author Yuval Linker Groisman
 */
public class Bow extends Weapon {

    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

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
}
