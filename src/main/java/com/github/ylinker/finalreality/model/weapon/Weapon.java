package com.github.ylinker.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public abstract class Weapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;
  private final int speed;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   *
   */
  public Weapon(final String name, final int damage, final int speed, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
    this.speed = speed;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public abstract boolean equals(final Object o);

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight());
  }
}