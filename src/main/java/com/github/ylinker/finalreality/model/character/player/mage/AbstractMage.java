package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the mage characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public abstract class AbstractMage extends AbstractPlayerCharacter implements IMage {

    protected final int mana;

    protected AbstractMage(@NotNull String name, final int health,
                           final int attack, final int defense,
                           final int mana) {
        super(name, health,  attack, defense);
        this.mana = mana;
    }

    /**
     * Get the mana of the mage
     * @return an int that represents the mana of the mage
     */
    @Override
    public int getMana() { return this.mana; }
}
