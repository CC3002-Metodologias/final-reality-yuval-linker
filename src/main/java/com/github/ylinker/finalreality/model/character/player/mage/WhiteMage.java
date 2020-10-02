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
     */
    public WhiteMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, final int mana) {
        super(turnsQueue, name, mana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

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
