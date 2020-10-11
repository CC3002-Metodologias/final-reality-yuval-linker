package com.github.ylinker.finalreality.model.character.player;

import com.github.ylinker.finalreality.model.character.AbstractCharacter;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import com.github.ylinker.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that holds the common behaviour of all the playable characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

    private IWeapon equippedWeapon = null;

    /**
     * The base constructor of every Playable Character.
     * @param turnsQueue
     *      The character's queue to wait it's turn
     * @param name
     *      The character's name
     */
    protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                      @NotNull String name) {
        super(name, turnsQueue);
    }

    /**
     * Adds the character to the queue and sets a delay based on weight
     */
    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, this.weight / 10, TimeUnit.SECONDS);
    }

    /**
     * Equips the character with a weapon
     * The weight of the character is now the weapon's weight
     * @param weapon
     *      The weapon that should be equipped
     */
    @Override
    public void equip(IWeapon weapon) {
        this.equippedWeapon = weapon;
        this.weight = weapon.getWeight();
    }

    /**
     * Gets the current weapon that the character is equipped with
     * @return the equipped weapon
     */
    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }
}
