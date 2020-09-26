package com.github.ylinker.finalreality.model.character.player.common;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.CharacterClass;
import com.github.ylinker.finalreality.model.character.player.PlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Knight extends AbstractCommon {

    public Knight(@NotNull BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name) {
        super(turnsQueue, name, CharacterClass.KNIGHT);
    }

    @Override
    public CharacterClass getCharacterClass() { return CharacterClass.KNIGHT; }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterClass());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight that = (Knight) o;
        return getCharacterClass() == that.getCharacterClass()
                && getName().equals(that.getName());
    }
}
