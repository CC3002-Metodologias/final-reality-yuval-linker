package com.github.ylinker.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;


/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public abstract class AbstractCharacter implements ICharacter {

    protected int weight;
    protected final BlockingQueue<ICharacter> turnsQueue;
    protected final String name;
    protected ScheduledExecutorService scheduledExecutor;

    public AbstractCharacter(@NotNull final String name,
                             @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        // Default weight is 10
        this.weight = 10;
        this.name = name;
        this.turnsQueue = turnsQueue;
    }

    /**
     * Add this character to the turns queue
     */
    protected void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
    }

    @Override
    public int getWeight(){
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract void waitTurn();

}
