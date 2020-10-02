package com.github.ylinker.finalreality.model.weapon;

/**
 * A Class that represents a Axe
 *
 * @author Yuval Linker Groisman
 */
public class Axe extends Weapon {

    public Axe(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe axe = (Axe) o;
        return getDamage() == axe.getDamage() &&
                getWeight() == axe.getWeight() &&
                getName().equals(axe.getName());
    }
}
