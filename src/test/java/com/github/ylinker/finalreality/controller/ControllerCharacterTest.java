package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.IMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ControllerCharacterTest {
    private GameController testController;

    @BeforeEach
    void setUp() {
        testController = new GameController();
        testController.setScene(new NullScene());
    }

    @Test
    void characterCreationTest() {
        testController.createEngineer("engineerTest", 10, 10, 0);
        assertTrue(testController.getCharacters().contains(
                new Engineer("engineerTest", 10, 10, 0)
                )
        );
        assertEquals(1, testController.getCharacters().size());
        assertEquals("Engineer", testController.getCharacterClass(testController.getCharacters().get(0)));
        testController.createKnight("knightTest", 10, 10, 25);
        assertTrue(testController.getCharacters().contains(
                new Knight("knightTest", 10, 10, 25)
                )
        );
        assertEquals(2, testController.getCharacters().size());
        assertEquals("Knight", testController.getCharacterClass(testController.getCharacters().get(1)));
        testController.createThief("thiefTest", 5, 20, 5);
        assertTrue(testController.getCharacters().contains(
                new Thief("thiefTest", 5, 20, 5)
                )
        );
        assertEquals(3, testController.getCharacters().size());
        assertEquals("Thief", testController.getCharacterClass(testController.getCharacters().get(2)));
        testController.createWhiteMage("whiteMageTest", 10, 10, 0, 20);
        assertTrue(testController.getCharacters().contains(
                new WhiteMage("whiteMageTest", 10, 10, 0, 20)
                )
        );
        assertEquals(4, testController.getCharacters().size());
        assertEquals("White Mage", testController.getCharacterClass(testController.getCharacters().get(3)));
        testController.createBlackMage("blackMageTest", 10, 20, 0, 15);
        assertTrue(testController.getCharacters().contains(
                new BlackMage("blackMageTest", 10, 20, 0, 15)
                )
        );
        assertEquals(5, testController.getCharacters().size());
        assertEquals("Black Mage", testController.getCharacterClass(testController.getCharacters().get(4)));
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
        testEngineer.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        Enemy testEnemy = testController.getEnemies().get(0);
        testEnemy.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());

        testController.attack(testEnemy, testEngineer);
        assertFalse(testController.getCharacters().contains(testEngineer));
        assertEquals(0, testController.getCharacters().size());

        testController.createKnight("knightTest", 10, 10, 0);
        testController.createThief("thiefTest", 10, 10, 0);
        Knight testKnight = (Knight) testController.getCharacters().get(0);
        testKnight.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        Thief testThief = (Thief) testController.getCharacters().get(1);
        testThief.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        assertEquals(2, testController.getCharacters().size());

        testController.attack(testEnemy, testKnight);
        assertFalse(testController.getCharacters().contains(testKnight));
        assertTrue(testKnight.getScheduledExecutor().isShutdown());
        assertEquals(1, testController.getCharacters().size());

        testController.createWhiteMage("whiteMageTest", 10, 10, 0, 20);
        testController.createBlackMage("blackMageTest", 10, 10, 0, 15);
        WhiteMage testWhiteMage = (WhiteMage) testController.getCharacters().get(1);
        testWhiteMage.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        BlackMage testBlackMage = (BlackMage) testController.getCharacters().get(2);

        testController.attack(testEnemy, testWhiteMage);
        assertFalse(testController.getCharacters().contains(testWhiteMage));
        assertTrue(testWhiteMage.getScheduledExecutor().isShutdown());
        assertEquals(2, testController.getCharacters().size());

        testController.attack(testEnemy, testThief);
        assertFalse(testController.getCharacters().contains(testThief));
        assertTrue(testThief.getScheduledExecutor().isShutdown());
        assertEquals(1, testController.getCharacters().size());

        testController.createEnemy("otherEnemy", 20, 10, 0, 10);
        Enemy otherEnemy = testController.getEnemies().get(1);

        testController.attack(testBlackMage, testEnemy);
        testController.attack(testBlackMage, testEnemy);
        assertFalse(testController.getEnemies().contains(testEnemy));
        assertTrue(testEnemy.getScheduledExecutor().isShutdown());
        assertEquals(1, testController.getEnemies().size());

        testController.attack(otherEnemy, testBlackMage);
        assertFalse(testController.getCharacters().contains(testBlackMage));
        assertEquals(0, testController.getCharacters().size());
        assertEquals(1, testController.getEnemies().size());

        testController.createKnight("playerWinner", 10, 20, 0);
        testKnight = (Knight) testController.getCharacters().get(0);
        testKnight.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        testController.attack(testKnight, otherEnemy);
        assertFalse(testController.getEnemies().contains(otherEnemy));
        assertEquals(0, testController.getEnemies().size());
    }

    @Test
    void getHealthTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        ICharacter knight = testController.getCharacters().get(0);
        Enemy enemy1 = testController.getEnemies().get(0);
        assertEquals(10, testController.getCharacterHealth(knight));
        assertEquals(10, testController.getCharacterHealth(enemy1));
        testController.createWhiteMage("testWhiteMage", 13, 10, 10, 5);
        testController.createEnemy("enemy2", 25, 10, 20, 1);
        ICharacter mage = testController.getCharacters().get(1);
        Enemy enemy2 = testController.getEnemies().get(1);
        assertEquals(13, testController.getCharacterHealth(mage));
        assertEquals(25, testController.getCharacterHealth(enemy2));
    }

    @Test
    void getAttackTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        ICharacter knight = testController.getCharacters().get(0);
        Enemy enemy1 = testController.getEnemies().get(0);
        assertEquals(10, testController.getCharacterAttack(knight));
        assertEquals(10, testController.getCharacterAttack(enemy1));
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createEnemy("enemy2", 25, 8, 20, 1);
        ICharacter mage = testController.getCharacters().get(1);
        Enemy enemy2 = testController.getEnemies().get(1);
        assertEquals(5, testController.getCharacterAttack(mage));
        assertEquals(8, testController.getCharacterAttack(enemy2));
    }

    @Test
    void getDefenseTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        ICharacter knight = testController.getCharacters().get(0);
        Enemy enemy1 = testController.getEnemies().get(0);
        assertEquals(10, testController.getCharacterDefense(knight));
        assertEquals(10, testController.getCharacterDefense(enemy1));
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createEnemy("enemy2", 25, 8, 20, 1);
        ICharacter mage = testController.getCharacters().get(1);
        Enemy enemy2 = testController.getEnemies().get(1);
        assertEquals(30, testController.getCharacterDefense(mage));
        assertEquals(20, testController.getCharacterDefense(enemy2));
    }

    @Test
    void namesTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("enemy1", 10, 10, 10, 10);
        ICharacter knight = testController.getCharacters().get(0);
        Enemy enemy1 = testController.getEnemies().get(0);
        assertEquals("testKnight", testController.getCharacterName(knight));
        assertEquals("enemy1", testController.getCharacterName(enemy1));
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createEnemy("enemy2", 25, 8, 20, 1);
        ICharacter mage = testController.getCharacters().get(1);
        Enemy enemy2 = testController.getEnemies().get(1);
        assertEquals("testWhiteMage", testController.getCharacterName(mage));
        assertEquals("enemy2", testController.getCharacterName(enemy2));
    }

    @Test
    void manaTest() {
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createBlackMage("testBlackMage", 13, 5, 30, 25);
        IMage white = (IMage) testController.getCharacters().get(0);
        IMage black = (IMage) testController.getCharacters().get(1);
        assertEquals(5, testController.getMageMana(white));
        assertEquals(25, testController.getMageMana(black));
    }

    @Test
    void getEquippedWeaponsTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createSword("sword", 12, 12);
        IPlayerCharacter knight = testController.getCharacters().get(0);
        IWeapon sword = testController.getInventory().get(0);
        testController.equip(knight, sword);
        assertEquals(sword, testController.getCharacterEquippedWeapon(knight));
        testController.createWhiteMage("testWhiteMage", 13, 5, 30, 5);
        testController.createStaff("staff", 45, 12, 1);
        IPlayerCharacter white = testController.getCharacters().get(1);
        IWeapon staff = testController.getInventory().get(0);
        testController.equip(white, staff);
        assertEquals(staff, testController.getCharacterEquippedWeapon(white));
    }
}
