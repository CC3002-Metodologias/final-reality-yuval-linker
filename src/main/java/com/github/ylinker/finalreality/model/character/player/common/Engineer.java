package com.github.ylinker.finalreality.model.character.player.common;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single engineer character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public class Engineer extends AbstractPlayerCharacter {
    /**
     * Creates a new engineer character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name) {
        super(turnsQueue, name);
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
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer that = (Engineer) o;
        return getName().equals(that.getName());
    }
}
