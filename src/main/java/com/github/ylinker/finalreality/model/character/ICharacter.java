package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.controller.handler.IEventHandler;

import java.util.concurrent.ScheduledExecutorService;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public interface ICharacter {
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
   * Gets the Scheduled Executor of this character
   * @return
   *    The scheduled executor
   */
  ScheduledExecutorService getScheduledExecutor();

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

  /**
   * Adds an observer for the character's death event
   * @param handler
   *    The Death Listener
   */
  void addDeathListener(IEventHandler handler);

  /**
   * Adds an observer for the character's turn beginning
   * @param handler
   *    The Turn Listener
   */
  void addBeginTurnListener(IEventHandler handler);

  /**
   * Warns for the event of starting this character's turn
   * For observers to act
   */
  void beginTurn();

  /**
   * Returns the character's delay based on their weight and turns formula
   * @return
   *    The amount of seconds of delay between turns
   */
  int getDelay();

  /**
   * Sets the Schedule Executor of this character
   * @param schedule
   *    The scheduled executor value
   */
  void setScheduledExecutor(ScheduledExecutorService schedule);

  /**
   * Shuts down the Scheduled Executor of this character
   */
  void shutdownScheduledExecutor();
}
