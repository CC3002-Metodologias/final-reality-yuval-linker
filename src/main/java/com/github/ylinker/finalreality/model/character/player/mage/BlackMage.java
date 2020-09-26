package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class BlackMage extends AbstractMage {

    public BlackMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name) {
        super(turnsQueue, name, CharacterClass.BLACK_MAGE);
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
