package com.github.ylinker.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage and it's weight
   *
   * @param name
   *    The weapon's name
   * @param damage
   *    The weapon's damage
   * @param weight
   *    The weapon's weight
   */
  protected AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Gets the wepon's name
   * @return a string containing the weapon's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the weapon's damage
   * @return an int with the weapon's damage
   */
  @Override
  public int getDamage() {
    return damage;
  }

  /**
   * Gets the weapon's weight
   * @return an int with the weapon's weight
   */
  @Override
  public int getWeight() {
    return weight;
  }

  /**
   * Tests if another object is equal to this weapon
   * @param o
   *    Other object
   * @return true if it is equal, false otherwise
   */
  @Override
  public abstract boolean equals(final Object o);

  /**
   * Makes a hashcode unique to this instance of a weapon
   * @return an int with the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight());
  }

  /**
   * Equips this AbstractWeapon to a Character of class Engineer
   * @return this weapon if its equippable, null otherwise
   */
  @Override
  public IWeapon equipToEngineer() {
    return null;
  }

  /**
   * Equips this AbstractWeapon to a Character of class Knight
   * @return this weapon if its equippable, null otherwise
   */
  @Override
  public IWeapon equipToKnight() {
    return null;
  }

  /**
   * Equips this AbstractWeapon to a Character of class Thief
   * @return this weapon if its equippable, null otherwise
   */
  @Override
  public IWeapon equipToThief() {
    return null;
  }

  /**
   * Equips this AbstractWeapon to a Character of class Black Mage
   * @return this weapon if its equippable, null otherwise
   */
  @Override
  public IWeapon equipToBlackMage() {
    return null;
  }

  /**
   * Equips this AbstractWeapon to a Character of class White Mage
   * @return this weapon if its equippable, null otherwise
   */
  @Override
  public IWeapon equipToWhiteMage() {
    return null;
  }

}