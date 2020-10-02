package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single black mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public class BlackMage extends AbstractMage {

    /**
     * Creates a new black mage character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param mana
     *     the character's mana
     */
    public BlackMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name, final int mana) {
        super(turnsQueue, name, CharacterClass.BLACK_MAGE, mana);
    }

    @Override
    public CharacterClass getCharacterClass() { return CharacterClass.BLACK_MAGE; }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterClass());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackMage)) {
            return false;
        }
        final BlackMage that = (BlackMage) o;
        return getCharacterClass() == that.getCharacterClass()
                && getName().equals(that.getName());
    }
}