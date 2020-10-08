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

    protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                      @NotNull String name) {
        super(name, turnsQueue);
    }

    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, this.weight / 10, TimeUnit.SECONDS);
    }

    @Override
    public void equip(IWeapon weapon) {
        this.equippedWeapon = weapon;
        this.weight = weapon.getWeight();
    }

    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }
}
