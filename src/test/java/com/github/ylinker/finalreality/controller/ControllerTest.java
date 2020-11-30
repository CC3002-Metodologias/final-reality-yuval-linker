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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private GameController testController;
    private HashMap<String, IPlayerCharacter> playerCharacters;
    private HashMap<String, Enemy> enemies;

    @BeforeEach
    void setUp() {
        testController = new GameController();

    }

    @Test
    void characterCreationTest() {
        testController.createEngineer("engineerTest", 10, 10, 0);
        assertTrue(testController.getCharacters().contains(
                new Engineer("engineerTest", 10, 10, 0)
                )
        );
        assertEquals(1, testController.getCharacters().size());
        testController.createKnight("knightTest", 10, 10, 25);
        assertTrue(testController.getCharacters().contains(
                new Knight("knightTest", 10, 10, 25)
                )
        );
        assertEquals(2, testController.getCharacters().size());
        testController.createThief("thiefTest", 5, 20, 5);
        assertTrue(testController.getCharacters().contains(
                new Thief("thiefTest", 5, 20, 5)
                )
        );
        assertEquals(3, testController.getCharacters().size());
        testController.createWhiteMage("whiteMageTest", 10, 10, 0, 20);
        assertTrue(testController.getCharacters().contains(
                new WhiteMage("whiteMageTest", 10, 10, 0, 20)
                )
        );
        assertEquals(4, testController.getCharacters().size());
        testController.createBlackMage("blackMageTest", 10, 20, 0, 15);
        assertTrue(testController.getCharacters().contains(
                new BlackMage("blackMageTest", 10, 20, 0, 15)
                )
        );
        assertEquals(5, testController.getCharacters().size());
        testController.createEnemy("enemyTest", 10, 5, 5, 10);
        assertTrue(testController.getEnemies().contains(
                new Enemy("enemyTest", 10, 5, 5, 10)
        ));
        assertEquals(1, testController.getEnemies().size());
    }

    @Test
    void characterDeathTest() {
        testController.createEngineer("engineerTest", 10, 10, 0);
        testController.createEnemy("enemyTest", 10, 10, 5, 10);
        Engineer testEngineer = (Engineer) testController.getCharacters().get(0);
        Enemy testEnemy = testController.getEnemies().get(0);

        testController.attack(testEnemy, testEngineer);
        assertFalse(testController.getCharacters().contains(testEngineer));
        assertEquals(0, testController.getCharacters().size());

        testController.createKnight("knightTest", 10, 10, 0);
        testController.createThief("thiefTest", 10, 10, 0);
        Knight testKnight = (Knight) testController.getCharacters().get(0);
        Thief testThief = (Thief) testController.getCharacters().get(1);
        assertEquals(2, testController.getCharacters().size());

        testController.attack(testEnemy, testKnight);
        assertFalse(testController.getCharacters().contains(testKnight));
        assertEquals(1, testController.getCharacters().size());

        testController.createWhiteMage("whiteMageTest", 10, 10, 0, 20);
        testController.createBlackMage("blackMageTest", 10, 10, 0, 15);
        WhiteMage testWhiteMage = (WhiteMage) testController.getCharacters().get(1);
        BlackMage testBlackMage = (BlackMage) testController.getCharacters().get(2);

        testController.attack(testEnemy, testWhiteMage);
        assertFalse(testController.getCharacters().contains(testWhiteMage));
        assertEquals(2, testController.getCharacters().size());

        testController.attack(testEnemy, testThief);
        assertFalse(testController.getCharacters().contains(testThief));
        assertEquals(1, testController.getCharacters().size());

        testController.createEnemy("otherEnemy", 20, 10, 0, 10);
        Enemy otherEnemy = testController.getEnemies().get(1);

        testController.attack(testBlackMage, testEnemy);
        testController.attack(testBlackMage, testEnemy);
        assertFalse(testController.getEnemies().contains(testEnemy));
        assertEquals(1, testController.getEnemies().size());

        testController.attack(otherEnemy, testBlackMage);
        assertFalse(testController.getCharacters().contains(testBlackMage));
        assertEquals(0, testController.getCharacters().size());
        assertEquals(1, testController.getEnemies().size());
    }

    @Test
    void winConditionTest() {
        assertTrue(testController.playerLost());
        assertTrue(testController.playerWon());
        testController.createEnemy("testEnemy", 12, 12, 12, 10);
        assertFalse(testController.playerWon());
        assertTrue(testController.playerLost());
        testController.createEngineer("testCharacter", 12, 12, 12);
        assertFalse(testController.playerLost());
        assertFalse(testController.playerWon());
        testController.getEnemies().clear();
        assertTrue(testController.playerWon());
        assertFalse(testController.playerLost());
    }

    @Test
    void weaponsTest() {
        // Characters to test equip
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createWhiteMage("testWhiteMage", 10, 10, 10, 5);
        testController.createThief("testThief", 10, 10, 10);
        IPlayerCharacter knight = testController.getCharacters().get(0);
        IPlayerCharacter mage = testController.getCharacters().get(1);
        IPlayerCharacter thief = testController.getCharacters().get(2);

        // Create weapons
        testController.createAxe("testAxe", 10, 5);
        assertEquals(1, testController.getInventory().size());
        IWeapon axe = testController.getInventory().get(0);
        assertTrue(testController.getInventory().contains(axe));
        testController.createBow("testAxe", 10, 5);
        assertEquals(2, testController.getInventory().size());
        IWeapon bow = testController.getInventory().get(1);
        assertTrue(testController.getInventory().contains(bow));
        testController.createKnife("testAxe", 10, 5);
        assertEquals(3, testController.getInventory().size());
        IWeapon knife = testController.getInventory().get(2);
        assertTrue(testController.getInventory().contains(knife));
        testController.createSword("testAxe", 10, 5);
        assertEquals(4, testController.getInventory().size());
        IWeapon sword = testController.getInventory().get(3);
        assertTrue(testController.getInventory().contains(sword));
        testController.createStaff("testAxe", 10, 5, 3);
        assertEquals(5, testController.getInventory().size());
        IWeapon staff = testController.getInventory().get(4);
        assertTrue(testController.getInventory().contains(staff));

        // Start equipping weapons
        testController.equip(knight, sword);
        assertFalse(testController.getInventory().contains(sword));
        assertEquals(sword, knight.getEquippedWeapon());
        testController.equip(knight, staff);
        assertTrue(testController.getInventory().contains(staff));
        assertEquals(sword, knight.getEquippedWeapon());
        testController.equip(knight, axe);
        assertFalse(testController.getInventory().contains(axe));
        assertTrue(testController.getInventory().contains(sword));
        assertEquals(axe, knight.getEquippedWeapon());
        testController.equip(knight, knife);
        assertFalse(testController.getInventory().contains(knife));
        assertTrue(testController.getInventory().contains(axe));
        assertEquals(knife, knight.getEquippedWeapon());
        testController.equip(knight, bow);
        assertTrue(testController.getInventory().contains(bow));
        assertFalse(testController.getInventory().contains(knife));
        assertEquals(knife, knight.getEquippedWeapon());
        testController.equip(mage, staff);
        assertFalse(testController.getInventory().contains(staff));
        assertEquals(staff, mage.getEquippedWeapon());
        testController.equip(thief, bow);
        assertFalse(testController.getInventory().contains(bow));
        assertEquals(bow, thief.getEquippedWeapon());
    }

    @Test
    void nonInventoryWeaponTest() {
        // Inventory is empty
        // Try equipping weapons with empty inventory
        testController.createKnight("testKnight", 10, 10, 10);
        IPlayerCharacter knight = testController.getCharacters().get(0);
        assertTrue(testController.getInventory().isEmpty());
        testController.equip(knight, new Knife("knife", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());
        testController.equip(knight, new Sword("sword", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());
        testController.equip(knight, new Bow("bow", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());;
        testController.equip(knight, new Staff("staff", 10, 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());;
        testController.equip(knight, new Axe("axe", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());
        // Start adding weapons but equip only weapons not in inventory
        testController.createKnife("knife", 10, 10);
        testController.equip(knight, new Sword("sword", 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createSword("sword", 10, 10);
        testController.equip(knight, new Axe("axe", 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createAxe("axe", 10, 10);
        testController.equip(knight, new Bow("bow", 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createBow("bow", 10, 10);
        testController.equip(knight, new Staff("staff", 10, 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createStaff("staff", 10, 10, 10);
    }

    @Test
    void getHealthTest() {
        HashMap<IPlayerCharacter, Integer> expectedCharacterHealth = new HashMap<>();
        HashMap<Enemy, Integer> expectedEnemyHealth = new HashMap<>();
        assertEquals(expectedCharacterHealth, testController.getCharactersHealth());
        assertEquals(expectedEnemyHealth, testController.getEnemiesHealth());
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        expectedCharacterHealth.put(testController.getCharacters().get(0), 10);
        expectedEnemyHealth.put(testController.getEnemies().get(0), 10);
        assertEquals(expectedCharacterHealth, testController.getCharactersHealth());
        assertEquals(expectedEnemyHealth, testController.getEnemiesHealth());
        testController.createWhiteMage("testWhiteMage", 13, 10, 10, 5);
        testController.createEnemy("enemy2", 25, 10, 20, 1);
        expectedCharacterHealth.put(testController.getCharacters().get(1), 13);
        expectedEnemyHealth.put(testController.getEnemies().get(1), 25);
        assertEquals(expectedCharacterHealth, testController.getCharactersHealth());
        assertEquals(expectedEnemyHealth, testController.getEnemiesHealth());
        expectedCharacterHealth.remove(testController.getCharacters().get(1));
        expectedEnemyHealth.remove(testController.getEnemies().get(1));
        testController.onEnemyDeath(testController.getEnemies().get(1));
        testController.onCharacterDeath(testController.getCharacters().get(1));
        assertEquals(expectedCharacterHealth, testController.getCharactersHealth());
        assertEquals(expectedEnemyHealth, testController.getEnemiesHealth());
    }

    @Test
    void getAttackTest() {
        HashMap<IPlayerCharacter, Integer> expectedCharacterAttack = new HashMap<>();
        HashMap<Enemy, Integer> expectedEnemyAttack = new HashMap<>();
        assertEquals(expectedCharacterAttack, testController.getCharactersAttack());
        assertEquals(expectedEnemyAttack, testController.getEnemiesAttack());
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        expectedCharacterAttack.put(testController.getCharacters().get(0), 10);
        expectedEnemyAttack.put(testController.getEnemies().get(0), 10);
        assertEquals(expectedCharacterAttack, testController.getCharactersAttack());
        assertEquals(expectedEnemyAttack, testController.getEnemiesAttack());
        testController.createWhiteMage("testWhiteMage", 13, 5, 10, 5);
        testController.createEnemy("enemy2", 25, 8, 20, 1);
        expectedCharacterAttack.put(testController.getCharacters().get(1), 5);
        expectedEnemyAttack.put(testController.getEnemies().get(1), 8);
        assertEquals(expectedCharacterAttack, testController.getCharactersAttack());
        assertEquals(expectedEnemyAttack, testController.getEnemiesAttack());
        expectedCharacterAttack.remove(testController.getCharacters().get(1));
        expectedEnemyAttack.remove(testController.getEnemies().get(1));
        testController.onEnemyDeath(testController.getEnemies().get(1));
        testController.onCharacterDeath(testController.getCharacters().get(1));
        assertEquals(expectedCharacterAttack, testController.getCharactersAttack());
        assertEquals(expectedEnemyAttack, testController.getEnemiesAttack());
    }

    @Test
    void getDefenseTest() {
        HashMap<IPlayerCharacter, Integer> expectedCharacterDefense = new HashMap<>();
        HashMap<Enemy, Integer> expectedEnemyDefense = new HashMap<>();
        assertEquals(expectedCharacterDefense, testController.getCharactersDefense());
        assertEquals(expectedEnemyDefense, testController.getEnemiesDefense());
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        expectedCharacterDefense.put(testController.getCharacters().get(0), 10);
        expectedEnemyDefense.put(testController.getEnemies().get(0), 10);
        assertEquals(expectedCharacterDefense, testController.getCharactersDefense());
        assertEquals(expectedEnemyDefense, testController.getEnemiesHealth());
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createEnemy("enemy2", 25, 8, 20, 1);
        expectedCharacterDefense.put(testController.getCharacters().get(1), 30);
        expectedEnemyDefense.put(testController.getEnemies().get(1), 20);
        assertEquals(expectedCharacterDefense, testController.getCharactersDefense());
        assertEquals(expectedEnemyDefense, testController.getEnemiesDefense());
        expectedCharacterDefense.remove(testController.getCharacters().get(1));
        expectedEnemyDefense.remove(testController.getEnemies().get(1));
        testController.onEnemyDeath(testController.getEnemies().get(1));
        testController.onCharacterDeath(testController.getCharacters().get(1));
        assertEquals(expectedCharacterDefense, testController.getCharactersDefense());
        assertEquals(expectedEnemyDefense, testController.getEnemiesDefense());
    }

    @Test
    void namesTest() {
        HashMap<IPlayerCharacter, String> expectedCharacterName = new HashMap<>();
        HashMap<Enemy, String> expectedEnemyName = new HashMap<>();
        assertEquals(expectedCharacterName, testController.getCharactersName());
        assertEquals(expectedEnemyName, testController.getEnemiesName());
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        expectedCharacterName.put(testController.getCharacters().get(0), "testKnight");
        expectedEnemyName.put(testController.getEnemies().get(0), "enemy1");
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createEnemy("enemy2", 25, 8, 20, 1);
        expectedCharacterName.put(testController.getCharacters().get(1), "testWhiteMage");
        expectedEnemyName.put(testController.getEnemies().get(1), "enemy2");
        assertEquals(expectedCharacterName, testController.getCharactersName());
        assertEquals(expectedEnemyName, testController.getEnemiesName());
        expectedCharacterName.remove(testController.getCharacters().get(1));
        expectedEnemyName.remove(testController.getEnemies().get(1));
        testController.onEnemyDeath(testController.getEnemies().get(1));
        testController.onCharacterDeath(testController.getCharacters().get(1));
        assertEquals(expectedCharacterName, testController.getCharactersName());
        assertEquals(expectedEnemyName, testController.getEnemiesName());
    }

    @Test
    void getEquippedWeaponsTest() {
        HashMap<IPlayerCharacter, IWeapon> expectedEquippedWeapon = new HashMap<>();
        assertEquals(expectedEquippedWeapon, testController.getCharactersEquippedWeapon());
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createSword("sword", 12, 12);
        expectedEquippedWeapon.put(testController.getCharacters().get(0), testController.getInventory().get(0));
        testController.equip(testController.getCharacters().get(0), testController.getInventory().get(0));
        assertEquals(expectedEquippedWeapon, testController.getCharactersEquippedWeapon());
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createStaff("staff", 45, 12, 1);
        expectedEquippedWeapon.put(testController.getCharacters().get(1), testController.getInventory().get(0));
        testController.equip(testController.getCharacters().get(1), testController.getInventory().get(0));
        assertEquals(expectedEquippedWeapon, testController.getCharactersEquippedWeapon());
        expectedEquippedWeapon.remove(testController.getCharacters().get(1));
        testController.onCharacterDeath(testController.getCharacters().get(1));
        assertEquals(expectedEquippedWeapon, testController.getCharactersEquippedWeapon());
    }

    @Test
    void addToQueueTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("testEnemy", 10, 10, 10, 20);
        BlockingQueue<ICharacter> queue = testController.getQueue();
        assertTrue(queue.isEmpty());
        IPlayerCharacter knight = testController.getCharacters().get(0);
        Enemy enemy = testController.getEnemies().get(0);
        knight.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        enemy.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        testController.addToQueue(knight);
        // On an empty queue the characters should immediately start their turn
        assertFalse(queue.contains(knight));
        assertTrue(queue.isEmpty());
        testController.addToQueue(enemy);
        assertFalse(queue.contains(enemy));
        assertTrue(queue.isEmpty());

        // Add dummy so that queue is not empty
        Enemy dummy = new Enemy("dummy", 1, 1, 1, 10);
        queue.add(dummy);

        knight.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        enemy.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        testController.addToQueue(knight);
        assertTrue(testController.getQueue().contains(knight));
        testController.addToQueue(enemy);
        assertTrue(testController.getQueue().contains(enemy));
    }

    @Test
    void turnInitTest() {
        // Create characters
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("testEnemy", 10, 10, 10, 20);
        testController.initTurns();
        assertNotNull(testController.getCharacters().get(0).getScheduledExecutor());
        assertNotNull(testController.getEnemies().get(0).getScheduledExecutor());
    }

}
