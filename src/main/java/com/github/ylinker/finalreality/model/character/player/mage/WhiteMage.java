package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.CharacterClass;
import com.github.ylinker.finalreality.model.character.player.PlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class WhiteMage extends AbstractMage {

    public WhiteMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(turnsQueue, name, CharacterClass.WHITE_MAGE);
    }

    @Override
    public CharacterClass getCharacterClass() { return CharacterClass.WHITE_MAGE; }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterClass());
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
        return getCharacterClass() == that.getCharacterClass()
                && getName().equals(that.getName());
    }


}
