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

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * The game controller.
 * It is the intermediary between the player and the model.
 * Controls the messages and actions between the player and the game
 */
public class GameController {
    private final ArrayList<IPlayerCharacter> playerCharacters;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<IWeapon> inventory;
    private final BlockingQueue<ICharacter> queue;
    private final IEventHandler characterDeadHandler = new PlayerCharacterDeadHandler(this);
    private final IEventHandler enemyDeadHandler = new EnemyDeadHandler(this);
    private final IEventHandler beginTurnHandler = new BeginTurnHandler(this);

    public GameController() {
        playerCharacters = new ArrayList<>();
        enemies = new ArrayList<>();
        inventory = new ArrayList<>();
        queue = new LinkedBlockingQueue<>();
    }

    public ArrayList<IPlayerCharacter> getCharacters() {
        return playerCharacters;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    public BlockingQueue<ICharacter> getQueue() {
        return queue;
    }

    /**
     * Add a new character to the player's characters
     * @param character
     *      The new character to add
     */
    private void addPlayerCharacter(IPlayerCharacter character){
        playerCharacters.add(character);
        character.addDeathListener(characterDeadHandler);
        character.addBeginTurnListener(beginTurnHandler);
    }

    public void createEngineer(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Engineer(name, health, attack, defense));
    }

    public void createKnight(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Knight(name, health, attack, defense));
    }

    public void createThief(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Thief(name, health, attack, defense));
    }

    public void createWhiteMage(@NotNull String name, int health, int attack, int defense, int mana){
        addPlayerCharacter(new WhiteMage(name, health, attack, defense, mana));
    }

    public void createBlackMage(@NotNull String name, int health, int attack, int defense, int mana){
        addPlayerCharacter(new BlackMage(name, health, attack, defense, mana));
    }

    public HashMap<IPlayerCharacter, Integer> getCharactersHealth() {
        HashMap<IPlayerCharacter, Integer> charactersHealth = new HashMap<>();
        for (IPlayerCharacter character: playerCharacters) {
            charactersHealth.put(character, character.getHealth());
        }
        return charactersHealth;
    }

    public HashMap<IPlayerCharacter, Integer> getCharactersAttack() {
        HashMap<IPlayerCharacter, Integer> charactersAttack = new HashMap<>();
        for (IPlayerCharacter character: playerCharacters) {
            charactersAttack.put(character, character.getAttack());
        }
        return charactersAttack;
    }

    public HashMap<IPlayerCharacter, Integer> getCharactersDefense() {
        HashMap<IPlayerCharacter, Integer> charactersDefense = new HashMap<>();
        for (IPlayerCharacter character: playerCharacters) {
            charactersDefense.put(character, character.getDefense());
        }
        return charactersDefense;
    }

    public HashMap<IPlayerCharacter, String> getCharactersName() {
        HashMap<IPlayerCharacter, String> charactersName = new HashMap<>();
        for (IPlayerCharacter character: playerCharacters) {
            charactersName.put(character, character.getName());
        }
        return charactersName;
    }

    public HashMap<IPlayerCharacter, IWeapon> getCharactersEquippedWeapon() {
        HashMap<IPlayerCharacter, IWeapon> charactersWeapon = new HashMap<>();
        for (IPlayerCharacter character: playerCharacters) {
            charactersWeapon.put(character, character.getEquippedWeapon());
        }
        return charactersWeapon;
    }

    private void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.addDeathListener(enemyDeadHandler);
        enemy.addBeginTurnListener(beginTurnHandler);
    }

    public void createEnemy(@NotNull String name, int health, int attack, int defense, int weight) {
        addEnemy(new Enemy(name, health, attack, defense, weight));
    }

    public HashMap<Enemy, Integer> getEnemiesHealth() {
        HashMap<Enemy, Integer> enemiesHealth = new HashMap<>();
        for (Enemy enemy: enemies) {
            enemiesHealth.put(enemy, enemy.getHealth());
        }
        return enemiesHealth;
    }

    public HashMap<Enemy, Integer> getEnemiesDefense() {
        HashMap<Enemy, Integer> enemiesDefense = new HashMap<>();
        for (Enemy enemy: enemies) {
            enemiesDefense.put(enemy, enemy.getDefense());
        }
        return enemiesDefense;
    }

    public HashMap<Enemy, Integer> getEnemiesAttack() {
        HashMap<Enemy, Integer> enemiesAttack = new HashMap<>();
        for (Enemy enemy: enemies) {
            enemiesAttack.put(enemy, enemy.getAttack());
        }
        return enemiesAttack;
    }

    public HashMap<Enemy, String> getEnemiesName() {
        HashMap<Enemy, String> enemiesNames = new HashMap<>();
        for (Enemy enemy: enemies) {
            enemiesNames.put(enemy, enemy.getName());
        }
        return enemiesNames;
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
        IWeapon previousWeapon = character.getEquippedWeapon();
        if (inventory.contains(weapon)) {
            if (character.equip(weapon)) {
                inventory.remove(weapon);
                if (previousWeapon != null) {
                    inventory.add(previousWeapon);
                }
            }
        }
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

    public void beginTurn() {
        ICharacter character = queue.peek();
        if (!(character == null)) {
            character.beginTurn();
            // Here the character's turn ends
            queue.poll();
            waitTurn(character);
            beginTurn();
        }
    }

    public void waitTurn(ICharacter character) {
        character.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        Runnable command = () -> this.addToQueue(character);
        character.getScheduledExecutor().schedule(command, character.getDelay(), TimeUnit.SECONDS);
    }

    public void addToQueue(ICharacter character) {
        if (queue.isEmpty()) {
            queue.add(character);
            character.shutdownScheduledExecutor();
            beginTurn();
        } else {
            queue.add(character);
            character.shutdownScheduledExecutor();
        }
    }

    public void initTurns() {
        for (IPlayerCharacter character: playerCharacters) {
            waitTurn(character);
        }
        for (Enemy enemy: enemies) {
            waitTurn(enemy);
        }
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
