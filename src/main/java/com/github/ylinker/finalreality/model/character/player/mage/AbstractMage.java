package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that holds the common behaviour of all the mage characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public abstract class AbstractMage extends AbstractPlayerCharacter implements IMage {

    protected final int mana;

    protected AbstractMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                @NotNull String name, final int mana) {
        super(turnsQueue, name);
        this.mana = mana;
    }

    /**
     * Get the mana of the mage
     * @return an int that represents the mana of the mage
     */
    @Override
    public int getMana() { return this.mana; }
}
