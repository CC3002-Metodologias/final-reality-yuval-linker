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

    /**
     * Constructs an Abstract Character. It's the base of every character's constructor.
     * @param name
     *      The character's name
     * @param turnsQueue
     *      The queue that the character uses to wait for it's turn
     */
    protected AbstractCharacter(@NotNull final String name,
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

    /**
     * Gets the character's weight
     * @return the character's weight
     */
    @Override
    public int getWeight(){
        return weight;
    }

    /**
     * Gets the character's name
     * @return the character's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method that manages how a character should wait its turn in the queue
     */
    public abstract void waitTurn();

}
