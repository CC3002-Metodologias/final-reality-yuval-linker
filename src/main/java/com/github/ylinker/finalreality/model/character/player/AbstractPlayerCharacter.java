package com.github.ylinker.finalreality.model.character.player;

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
public abstract class AbstractPlayerCharacter implements ICharacter, IPlayerCharacter {

    protected final BlockingQueue<ICharacter> turnsQueue;
    protected final String name;
    private IWeapon equippedWeapon = null;
    private ScheduledExecutorService scheduledExecutor;

    protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                      @NotNull String name) {
        this.turnsQueue = turnsQueue;
        this.name = name;
    }

    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);

    }

    /**
     * Adds this character to the turns queue.
     */
    private void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void equip(IWeapon weapon) {
            this.equippedWeapon = weapon;
    }

    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }
}
