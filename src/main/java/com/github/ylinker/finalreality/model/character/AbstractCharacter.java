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

    protected int baseWeight;
    protected final BlockingQueue<ICharacter> turnsQueue;
    protected final String name;
    protected int health;
    protected int baseAttack;
    protected int defense;
    protected ScheduledExecutorService scheduledExecutor;

    /**
     * Constructs an Abstract Character. It's the base of every character's constructor.
     * @param name
     *      The character's name
     * @param turnsQueue
     *      The queue that the character uses to wait for it's turn
     * @param health
     *      The character's initial health points
     * @param attack
     *      The character's base attack
     * @param defense
     *      The character's initial defense
     */
    protected AbstractCharacter(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                                @NotNull final String name, final int health,
                                final int attack, final int defense) {
        // Default weight is 10
        this.baseWeight = 10;
        this.name = name;
        this.turnsQueue = turnsQueue;
        this.health = health;
        this.baseAttack = attack;
        this.defense = defense;
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

    private void setHealth(final int newHealth) {
        this.health = newHealth;
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
            System.out.println(this.name + " can't attack while dead!");
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
     * Method that manages how a character should wait its turn in the queue
     */
    public abstract void waitTurn();

}
