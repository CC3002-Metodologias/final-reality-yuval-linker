package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single white mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public class WhiteMage extends AbstractMage {

    /**
     * Creates a new white mage character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param mana
     *     the character's mana
     * @param attack
     *     the character's attack points
     * @param defense
     *     the character's defense points
     * @param health
     *      The character's initial health points
     * @param attack
     *      The character's initial attack
     * @param defense
     *      The character's initial defense
     */
    public WhiteMage(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name,
                     final int health, final int attack, final int defense, final int mana) {
        super(turnsQueue, name, health, attack, defense, mana);
    }

    /**
     * Makes a unique hashcode for this knight
     * @return an int that is the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Test another object to see if it is equal to this white mage
     * @param o
     *    The other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage that = (WhiteMage) o;
        return getName().equals(that.getName());
    }


}
