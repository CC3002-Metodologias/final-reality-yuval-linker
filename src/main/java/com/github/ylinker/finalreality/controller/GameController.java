package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.controller.handler.*;
import com.github.ylinker.finalreality.controller.phase.BeginTurnPhase;
import com.github.ylinker.finalreality.controller.phase.SelectAttackingTargetPhase;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidActionException;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.controller.phase.Phase;
import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.IMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import com.github.ylinker.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    private Phase phase;
    private ICharacter currentTurnCharacter;

    private final IEventHandler characterDeadHandler = new PlayerCharacterDeadHandler(this);
    private final IEventHandler enemyDeadHandler = new EnemyDeadHandler(this);
    private final IEventHandler characterTurnHandler = new PlayerCharacterTurnHandler(this);
    private final IEventHandler enemyTurnHandler = new EnemyTurnHandler(this);

    /**
     * Creates a Game Controller with an initial empty
     *      Player roster
     *      Enemy Roster
     *      Player Inventory
     *      Queue
     *      And initial Begin Turn Phase
     */
    public GameController() {
        playerCharacters = new ArrayList<>();
        enemies = new ArrayList<>();
        inventory = new ArrayList<>();
        queue = new LinkedBlockingQueue<>();
        setPhase(new BeginTurnPhase());
    }

    /**
     * Method to know if the player Won
     * The player wins when there aren't any enemies left
     * @return
     *      True if the player won, False otherwise
     */
    public boolean winCondition() {
        return enemies.isEmpty();
    }

    /**
     * Method that manages the event of the player winning
     */
    public void playerWon() {

    }

    /**
     * Method to know if the player lost
     * The player loses if there aren't any player characters left
     * @return
     *      True if the player lost, False otherwise
     */
    public boolean loseCondition() {
        return playerCharacters.isEmpty();
    }

    /**
     * Method that manages the event of the player losing
     */
    public void playerLost() {

    }

    /**
     * Gets the entire player roster of characters
     * @return
     *      An array list containing every alive player character
     */
    public ArrayList<IPlayerCharacter> getCharacters() {
        return playerCharacters;
    }

    /**
     * Gets all of the enemies
     * @return
     *      An array list containing every alive enemy
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets the player's inventory
     * @return
     *      An array list with every weapon not equipped
     */
    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * Gets the controllers queue that manages the turn order of the characters
     * @return
     *      The controllers blocking queue
     */
    public BlockingQueue<ICharacter> getQueue() {
        return queue;
    }

    /**
     * Gets the current turn character
     * @return
     *      A character
     */
    public ICharacter getCurrentTurnCharacter() {
        return currentTurnCharacter;
    }

    /**
     * Creates an Engineer for the player and adds it to the player's roster
     * @param name
     *      The character's name
     * @param health
     *      The character's health
     * @param attack
     *      The character's attack
     * @param defense
     *      The character's defense
     */
    public void createEngineer(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Engineer(name, health, attack, defense));
    }

    /**
     * Creates a Knight for the player and adds it to the player's roster
     * @param name
     *      The character's name
     * @param health
     *      The character's health
     * @param attack
     *      The character's attack
     * @param defense
     *      The character's defense
     */
    public void createKnight(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Knight(name, health, attack, defense));
    }

    /**
     * Creates a Thief for the player and adds it to the player's roster
     * @param name
     *      The character's name
     * @param health
     *      The character's health
     * @param attack
     *      The character's attack
     * @param defense
     *      The character's defense
     */
    public void createThief(@NotNull String name, int health, int attack, int defense){
        addPlayerCharacter(new Thief(name, health, attack, defense));
    }

    /**
     * Creates a White Mage for the player and adds it to the player's roster
     * @param name
     *      The character's name
     * @param health
     *      The character's health
     * @param attack
     *      The character's attack
     * @param defense
     *      The character's defense
     */
    public void createWhiteMage(@NotNull String name, int health, int attack, int defense, int mana){
        addPlayerCharacter(new WhiteMage(name, health, attack, defense, mana));
    }

    /**
     * Creates a Black Mage for the player and adds it to the player's roster
     * @param name
     *      The character's name
     * @param health
     *      The character's health
     * @param attack
     *      The character's attack
     * @param defense
     *      The character's defense
     */
    public void createBlackMage(@NotNull String name, int health, int attack, int defense, int mana){
        addPlayerCharacter(new BlackMage(name, health, attack, defense, mana));
    }

    /**
     * Gets a character's health
     * @param character
     *      The character
     * @return
     *      The character's health
     */
    public int getCharacterHealth(ICharacter character) {
        return character.getHealth();
    }

    /**
     * Gets the character attack
     * @param character
     *      The character
     * @return
     *      The character's attack
     */
    public int getCharacterAttack(ICharacter character) {
        return character.getAttack();
    }

    /**
     * Gets the character's Defense
     * @param character
     *      The character
     * @return
     *      The character's defense
     */
    public int getCharacterDefense(ICharacter character) {
        return character.getDefense();
    }

    /**
     * Gets the character's name
     * @param character
     *      The character
     * @return
     *      The character's name
     */
    public String getCharacterName(ICharacter character) {
        return character.getName();
    }

    /**
     * Gets the Player Character's equipped weapon
     * @param character
     *      The player character
     * @return
     *      The character's equipped weapon
     */
    public IWeapon getCharacterEquippedWeapon(IPlayerCharacter character) {
        return character.getEquippedWeapon();
    }

    /**
     * Gets the mage's mana
     * @param mage
     *      The mage
     * @return
     *      The mage's mana
     */
    public int getMageMana(IMage mage) {
        return mage.getMana();
    }

    /**
     * Creates an enemy and adds it to the enemies roster
     * @param name
     *      The enemy name
     * @param health
     *      The enemy health
     * @param attack
     *      The enemy attack
     * @param defense
     *      The enemy defense
     * @param weight
     *      The enemy weight
     */
    public void createEnemy(@NotNull String name, int health, int attack, int defense, int weight) {
        addEnemy(new Enemy(name, health, attack, defense, weight));
    }

    /**
     * Creates a weapon and adds it to the inventory
     * @param name
     *      The axe name
     * @param damage
     *      The axe damage
     * @param weight
     *      The axe weight
     */
    public void createAxe(@NotNull String name, final int damage, final int weight){
        addWeapon(new Axe(name, damage, weight));
    }

    /**
     * Creates a bow and adds it to the inventory
     * @param name
     *      The bow name
     * @param damage
     *      The bow damage
     * @param weight
     *      The bow weight
     */
    public void createBow(@NotNull String name, final int damage, final int weight){
        addWeapon(new Bow(name, damage, weight));
    }

    /**
     * Creates a knife and adds it to the inventory
     * @param name
     *      The knife name
     * @param damage
     *      The knife damage
     * @param weight
     *      The knife weight
     */
    public void createKnife(@NotNull String name, final int damage, final int weight){
        addWeapon(new Knife(name, damage, weight));
    }

    /**
     * Creates a sword and adds it to the inventory
     * @param name
     *      The sword name
     * @param damage
     *      The sword damage
     * @param weight
     *      The sword weight
     */
    public void createSword(@NotNull String name, final int damage, final int weight){
        addWeapon(new Sword(name, damage, weight));
    }

    /**
     * Creates a staff and adds it to the inventory
     * @param name
     *      The staff name
     * @param damage
     *      The staff damage
     * @param weight
     *      The staff weight
     * @param magicDamage
     *      The staff magic damage
     */
    public void createStaff(@NotNull String name, final int damage, final int weight, final int magicDamage){
        addWeapon(new Staff(name, damage, weight, magicDamage));
    }

    /**
     * Gets the weapon's name
     * @param weapon
     *      The weapon
     * @return
     *      The weapon's name
     */
    public String getWeaponName(IWeapon weapon) {
        return weapon.getName();
    }

    /**
     * Gets the weapon's damage
     * @param weapon
     *      The weapon
     * @return
     *      The wepoan's damage
     */
    public int getWeaponDamage(IWeapon weapon) {
        return weapon.getDamage();
    }

    /**
     * Gets the weapon's weight
     * @param weapon
     *      The weapon
     * @return
     *      The weapons weight
     */
    public int getWeaponWeight(IWeapon weapon) {
        return weapon.getWeight();
    }

    /**
     * Gets the staff's magic damage
     * @param staff
     *      The staff
     * @return
     *      The staff's magic damage
     */
    public int getStaffMagicDamage(Staff staff) {
        return staff.getMagicDamage();
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

    /**
     * Method to begin a new turn.
     * If the queue is empty then it does nothing
     * If the queue has a character it starts its turn
     */
    public void beginTurn() {
        ICharacter character = queue.peek();
        if (!(character == null)) {
            currentTurnCharacter = character;
            character.beginTurn();
        }
    }

    /**
     * Method to make a character wait to be added again to the queue
     * based on the character's delay
     * @param character
     *      The character that has to wait for his turn
     */
    public void waitTurn(ICharacter character) {
        character.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        Runnable command = () -> this.addToQueue(character);
        character.getScheduledExecutor().schedule(command, character.getDelay(), TimeUnit.SECONDS);
    }

    /**
     * Adds a character to the turns queue
     * If the queue is empty then it immediately starts its turn
     * @param character
     *      The character to be added
     */
    public void addToQueue(ICharacter character) {
        if (queue.isEmpty()) {
            queue.add(character);
            character.shutdownScheduledExecutor();
            phase.beginTurn();
        } else {
            queue.add(character);
            character.shutdownScheduledExecutor();
        }
    }

    /**
     * Method to start the turns of every player character and enemy.
     */
    public void initTurns() {
        for (IPlayerCharacter character: playerCharacters) {
            waitTurn(character);
        }
        for (Enemy enemy: enemies) {
            waitTurn(enemy);
        }
    }

    /**
     * Method to set the phase that the controller is currently in
     * @param phase
     *      The phase that the controller has to be set
     */
    public void setPhase(@NotNull Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * Method to set the character's phase
     * @param character
     *      The character that is passed to tha phase
     */
    public void setPhaseCharacter(@NotNull ICharacter character){
        phase.setCharacter(character);
    }

    /**
     * Removes the player character from the game when it dies
     * @param character
     *      The dead character
     */
    public void onCharacterDeath(IPlayerCharacter character) {
        playerCharacters.remove(character);
        queue.remove(character);
    }

    /**
     * Remove the enemy from the game when it dies
     * @param enemy
     *      The dead enemy
     */
    public void onEnemyDeath(Enemy enemy) {
        enemies.remove(enemy);
        queue.remove(enemy);
    }

    /**
     * Add a new enemy to the enemies roster
     * @param enemy
     *      The enemy to be added
     */
    private void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.addDeathListener(enemyDeadHandler);
        enemy.addBeginTurnListener(enemyTurnHandler);
    }

    /**
     * Add a new character to the player's characters
     * @param character
     *      The new character to add
     */
    private void addPlayerCharacter(IPlayerCharacter character){
        playerCharacters.add(character);
        character.addDeathListener(characterDeadHandler);
        character.addBeginTurnListener(characterTurnHandler);
    }

    /**
     * Add a weapon to the player's inventory
     * @param weapon
     *      The weapon to add
     */
    private void addWeapon(IWeapon weapon){
        inventory.add(weapon);
    }

    /**
     * Method to try to Attack.
     * If its not a phase in which you can attack then does nothing.
     * After attacking it knows its the end of the turn so passes to a begin turn phase
     * @param character
     *      The character that is being attacked
     */
    public void tryToAttack(ICharacter character) {
        try {
            phase.selectTarget(character);
            // Here the character's turn ends
            ICharacter turnCharacter = queue.poll();
            waitTurn(turnCharacter);
            phase.beginTurn();
        } catch (InvalidActionException e) {
            // For now we do nothing
        }
    }

    /**
     * Method to try to equip a weapon.
     * If its not a phase in which you can equip a weapon then does nothing.
     * After equipping it goes back to the selection phase.
     * @param weapon
     *      The weapon that is being equipped.
     */
    public void tryToEquip(IWeapon weapon) {
        try {
            phase.selectWeapon(weapon);
            phase.toSelectActionPhase();
        } catch (InvalidActionException | InvalidTransitionException e) {
            // For now we do nothing
        }
    }

    /**
     * Method to go to the Equipping Phase.
     * If its not a valid transition then does nothing.
     */
    public void toEquipPhase() {
        try {
            phase.toSelectWeaponPhase();
        } catch (InvalidTransitionException e) {
            // For now we do nothing
        }
    }

    /**
     * Method to go to the Select Attacking Target Phase.
     * If its not a valid transition then does nothing.
     */
    public void toAttackPhase() {
        try {
            phase.toSelectAttackingTargetPhase();
        } catch (InvalidTransitionException e) {
            // For now we do nothing
        }
    }

    /**
     * Method to go to the Select Action Phase.
     * If its not a valid transition then does nothing.
     */
    public void toActionPhase() {
        try {
            phase.toSelectActionPhase();
        } catch (InvalidTransitionException e) {
            // For now we do nothing
        }
    }

    /**
     * Current Phase Getter
     * @return
     *      Current turn Phase
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * Method to go back to a previous phase.
     * If its not a valid transition then does nothing.
     */
    public void goBack() {
        try {
            phase.goBack();
        } catch (InvalidTransitionException e) {
            // Do nothing for now
        }
    }
}
