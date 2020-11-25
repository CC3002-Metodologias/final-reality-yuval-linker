package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import com.github.ylinker.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The game controller.
 * It is the intermediary between the player and the model.
 * Controls the messages and actions between the player and the game
 */
public class GameController {
    private final LinkedHashSet<IPlayerCharacter> playerCharacters;
    private final LinkedHashSet<Enemy> enemies;
    private LinkedHashSet<IWeapon> inventory;
    private final BlockingQueue<ICharacter> queue;
    private final IEventHandler characterDeadHandler = new PlayerCharacterDeadHandler(this);
    private final IEventHandler enemyDeadHandler = new EnemyDeadHandler(this);

    public GameController() {
        playerCharacters = new LinkedHashSet<>();
        enemies = new LinkedHashSet<>();
        inventory = new LinkedHashSet<>();
        queue = new LinkedBlockingQueue<>();
    }

    /**
     * Add a new character to the player's characters
     * @param character
     *      The new character to add
     */
    private void addPlayerCharacter(IPlayerCharacter character){
        playerCharacters.add(character);
        character.addListener(characterDeadHandler);
    }

    public void createEngineer(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Engineer(this.queue, name, health, attack, defense));
    }

    public void createKnight(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Knight(this.queue, name, health, attack, defense));
    }

    public void createThief(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Thief(this.queue, name, health, attack, defense));
    }

    public void createWhiteMage(@NotNull String name, int health, int attack, int defense, int mana){
        addPlayerCharacter(new WhiteMage(this.queue, name, health, attack, defense, mana));
    }

    public void createBlackMage(@NotNull String name, int health, int attack, int defense, int mana){
        addPlayerCharacter(new BlackMage(this.queue, name, health, attack, defense, mana));
    }

    private void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.addListener(enemyDeadHandler);
    }

    public void createEnemy(@NotNull String name, int health, int attack, int defense, int weight) {
        addEnemy(new Enemy(queue, name, health, attack, defense, weight));
    }

    /**
     * Add a weapon to the player's inventory
     * @param weapon
     *      The weapon to add
     */
    private void addWeapon(IWeapon weapon){
        inventory.add(weapon);
    }

    public void createAxe(@NotNull String name, final int damage, final int weight){
        addWeapon(new Axe(name, damage, weight));
    }

    public void createBow(@NotNull String name, final int damage, final int weight){
        addWeapon(new Bow(name, damage, weight));
    }

    public void createKnife(@NotNull String name, final int damage, final int weight){
        addWeapon(new Knife(name, damage, weight));
    }

    public void createSword(@NotNull String name, final int damage, final int weight){
        addWeapon(new Sword(name, damage, weight));
    }

    public void createStaff(@NotNull String name, final int damage, final int weight, final int magicDamage){
        addWeapon(new Staff(name, damage, weight, magicDamage));
    }

    /**
     * Equip a player's character with a weapon from the inventory
     * @param character
     *      The character that is going to equip the weapon
     * @param weapon
     *      The weapon to be equipped
     */
    public void equip(IPlayerCharacter character, IWeapon weapon){
        character.equip(weapon);
    }

    /**
     * Method simulating one character attacking another
     * @param attacker
     *      The character that is attacking
     * @param attacked
     *      The character that is being attacked
     */
    public void attack(ICharacter attacker, ICharacter attacked){
        attacker.attack(attacked);
    }

    public ICharacter beginTurn() throws InterruptedException {
        if (queue.isEmpty()) {
            Thread.sleep(6000);
            return beginTurn();
        } else {
            return queue.poll();
        }
    }

    public void endTurn(ICharacter character) {
        character.waitTurn();
    }

    public void onCharacterDeath(IPlayerCharacter character) {
        playerCharacters.remove(character);
        queue.remove(character);
    }

    public void onEnemyDeath(Enemy enemy) {
        enemies.remove(enemy);
        queue.remove(enemy);
    }

    public boolean playerWon() {
        return enemies.isEmpty();
    }

    public boolean playerLost() {
        return playerCharacters.isEmpty();
    }
}
