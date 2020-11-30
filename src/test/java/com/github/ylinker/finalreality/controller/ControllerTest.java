package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;
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
}
