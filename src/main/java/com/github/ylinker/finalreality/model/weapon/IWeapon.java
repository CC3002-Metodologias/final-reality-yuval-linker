package com.github.ylinker.finalreality.model.weapon;

public interface IWeapon {
    public String getName();

    public int getDamage();

    public int getWeight();

    public int getSpeed();

    public boolean equals(final Object o);

    public int hashCode();
}
