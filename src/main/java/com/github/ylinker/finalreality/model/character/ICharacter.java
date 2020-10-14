package com.github.ylinker.finalreality.model.character;

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
}
