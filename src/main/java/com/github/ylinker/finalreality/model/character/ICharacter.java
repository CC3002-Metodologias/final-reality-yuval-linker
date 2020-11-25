package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.controller.IEventHandler;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's weight
   */
  int getWeight();

  /**
   * Returns this character's attack
   */
  int getAttack();

  /**
   * Returns this character's defense
   */
  int getDefense();

  /**
   * Returns this character's Health points
   */
  int getHealth();

  /**
   * Checks if the character is alive (has more than 0 health)
   * @return true if the character has more than 0 health, false otherwise
   */
  boolean isAlive();

  /**
   * Attacks another character if and only if this character is alive
   *
   * @param other
   *      The other character that is being attacked
   *
   * @return the amount of damage done
   */
  int attack(ICharacter other);

  /**
   * Gets attacked by another character.
   * It calculates damage and applies it to its own health.
   * The damage calculation is done with the following formula
   *    damage = attacker.attack − attacked.defense
   *
   * @param otherAttack
   *      The attacking character's attack points
   *
   * @return the amount of damage done to this character
   */
  int defend(int otherAttack);

  void addListener(IEventHandler handler);
}
