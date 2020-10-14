package com.github.ylinker.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Yuval Linker
 */
public class Enemy extends AbstractCharacter {

  /**
   * Creates a new enemy
   *
   * @param name
   *    The enemy's name
   * @param weight
   *    the enemy's weight
   * @param turnsQueue
   *    the queue with the characters waiting for their turn
   * @param health
   *      The character's initial health points
   * @param attack
   *      The character's initial attack
   * @param defense
   *      The character's initial defense
   */
  public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue,
               @NotNull final String name, final int health,
               final int attack, final int defense, final int weight) {
    super(turnsQueue, name, health, attack, defense);
    this.weight = weight;
  }

  /**
   * Puts the enemy in the queue with a delay equals to its weight
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    var enemy = (Enemy) this;
    scheduledExecutor.schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }

  /**
   * Test another object to see if it is equal to this enemy
    * @param o
   *    The other object
   * @return true if it is equal, false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight() &&
            getName().equals(enemy.getName());
  }

  /**
   * Makes a unique hashcode for this enemy
   * @return an int that is the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getWeight());
  }
}
