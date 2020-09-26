package com.github.ylinker.finalreality.model.character.player.common;

import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.CharacterClass;
import com.github.ylinker.finalreality.model.character.player.PlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractCommon implements ICharacter, IPlayerCharacter {

    protected final BlockingQueue<ICharacter> turnsQueue;
    protected final String name;
    private final CharacterClass characterClass;
    private Weapon equippedWeapon = null;
    private ScheduledExecutorService scheduledExecutor;

    protected AbstractCommon(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                @NotNull String name, CharacterClass characterClass) {
        this.turnsQueue = turnsQueue;
        this.name = name;
        this.characterClass = characterClass;
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
    public void equip(Weapon weapon) {
            this.equippedWeapon = weapon;
    }

    @Override
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    @Override
    public abstract CharacterClass getCharacterClass();
}
