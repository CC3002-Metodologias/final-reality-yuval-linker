package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.controller.IEventHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.beans.PropertyChangeSupport;


/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public abstract class AbstractCharacter implements ICharacter {

    protected int baseWeight;
    protected final String name;
    protected int health;
    protected int baseAttack;
    protected int defense;
    protected ScheduledExecutorService scheduledExecutor;
    protected final PropertyChangeSupport characterDeadEvent = new PropertyChangeSupport(this);
    protected final PropertyChangeSupport beginTurnEvent = new PropertyChangeSupport(this);


    /**
     * Constructs an Abstract Character. It's the base of every character's constructor.
     * @param name
     *      The character's name
     * @param health
     *      The character's initial health points
     * @param attack
     *      The character's base attack
     * @param defense
     *      The character's initial defense
     */
    protected AbstractCharacter(@NotNull final String name, final int health,
                                final int attack, final int defense) {
        // Default weight is 10
        this.baseWeight = 10;
        this.name = name;
        this.health = health;
        this.baseAttack = attack;
        this.defense = defense;
    }

    /**
     * Gets the character's weight
     * @return the character's weight
     */
    @Override
    public int getWeight(){
        return baseWeight;
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
     * Gets the character's attack
     */
    @Override
    public int getAttack() {
        return baseAttack;
    }

    /**
     * Gets the character's defense
     */
    @Override
    public int getDefense() {
        return defense;
    }

    /**
     * Gets the character's health points
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Gets the Scheduled Executor of this character
     * @return
     *    The scheduled executor
     */
    @Override
    public ScheduledExecutorService getScheduledExecutor() {
        return scheduledExecutor;
    }

    /**
     * Returns the character's delay based on their weight and turns formula
     * @return
     *    The amount of seconds of delay between turns
     */
    @Override
    public int getDelay() {
        return getWeight()/10;
    }

    /**
     * Sets the Schedule Executor of this character
     * @param schedule
     *    The scheduled executor value
     */
    @Override
    public void setScheduledExecutor(ScheduledExecutorService schedule) {
        scheduledExecutor = schedule;
    }

    /**
     * Shuts down the Scheduled Executor of this character
     */
    @Override
    public void shutdownScheduledExecutor() {
        scheduledExecutor.shutdown();
    }

    private void setHealth(final int newHealth) {
        this.health = newHealth;
        if(!isAlive()) {
            characterDeadEvent.firePropertyChange("Is Dead", null, this);
        }
    }

    private void receiveDamage(final int damage) {
        setHealth(Math.max(this.health - damage, 0));
    }

    /**
     * Checks if the character is alive (has more than 0 health)
     * @return true if the character has more than 0 health, false otherwise
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public int attack(ICharacter other) {
        if (this.isAlive()) {
            return other.defend(this.getAttack());
        } else {
            // System.out.println(this.name + " can't attack while dead!");
            return -1;
        }
    }

    @Override
    public int defend(int otherAttack) {
        int damage = otherAttack - getDefense();
        if(damage > 0){
            receiveDamage(damage);
            return damage;
        } else {
            return 0;
        }
    }

    /**
     * Adds an observer for the character's death event
     * @param handler
     *    The Death Listener
     */
    @Override
    public void addDeathListener(IEventHandler handler) {
        characterDeadEvent.addPropertyChangeListener(handler);
    }

    /**
     * Adds an observer for the character's turn beginning
     * @param handler
     *    The Turn Listener
     */
    @Override
    public void addBeginTurnListener(IEventHandler handler) {
        beginTurnEvent.addPropertyChangeListener(handler);
    }

    /**
     * Warns for the event of starting this character's turn
     * For observers to act
     */
    @Override
    public void beginTurn() {
        beginTurnEvent.firePropertyChange("Begin Turn", null, this);
    }
}
