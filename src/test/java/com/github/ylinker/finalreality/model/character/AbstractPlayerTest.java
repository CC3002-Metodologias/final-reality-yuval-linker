package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractPlayerTest extends AbstractCharacterTest {

    protected Map<String, IWeapon> weapons;
    protected Enemy testEnemy;
    protected Enemy overkillEnemy;
    protected Enemy notKillableEnemy;
    protected Enemy notDamagableEnemy;
    protected IPlayerCharacter testPlayer;


    @BeforeEach
    void setUp() {
        basicSetUp();
        weapons = new HashMap<>();
        weapons.put("axe", new Axe("axeTest", 10, 5));
        weapons.put("bow", new Bow("bowTest", 15, 7));
        weapons.put("knife", new Knife("knife", 7, 4));
        weapons.put("staff", new Staff("staff", 5, 17, 30));
        weapons.put("sword", new Sword("sword", 25, 20));

        Random rand = new Random();
        testEnemy = new Enemy(turns, "testEnemy", 15, 0, 5, 10);
        overkillEnemy = new Enemy(turns, "testEnemy", 15 - (1 + rand.nextInt(4)), 0, 5, 10);
        notDamagableEnemy = new Enemy(turns, "testEnemy", 15, 0, 20, 10);
    }

    @Test
    void attackTest() {
        // Test exactly killable enemy
        int health = testEnemy.getHealth();
        assertTrue(testEnemy.isAlive());
        assertEquals(5, testCommon.attack(testEnemy));
        assertEquals(health-5, testEnemy.getHealth());
        assertTrue(testEnemy.isAlive());
        assertEquals(5, testCommon.attack(testEnemy));
        assertEquals(health-10, testEnemy.getHealth());
        assertTrue(testEnemy.isAlive());
        assertEquals(5, testCommon.attack(testEnemy));
        assertEquals(0, testEnemy.getHealth());
        assertFalse(testEnemy.isAlive());

        // Test overkilling the enemy
        health = overkillEnemy.getHealth();
        assertTrue(overkillEnemy.isAlive());
        assertEquals(5, testCommon.attack(overkillEnemy));
        assertEquals(health-5, overkillEnemy.getHealth());
        assertTrue(overkillEnemy.isAlive());
        assertEquals(5, testCommon.attack(overkillEnemy));
        assertEquals(health-10, overkillEnemy.getHealth());
        assertTrue(overkillEnemy.isAlive());
        assertEquals(5, testCommon.attack(overkillEnemy));
        assertEquals(0, overkillEnemy.getHealth());
        assertFalse(testEnemy.isAlive());

        // Test not dealing damage to enemy
        health = notDamagableEnemy.getHealth();
        assertEquals(0, testCommon.attack(notDamagableEnemy));
        assertEquals(health, notDamagableEnemy.getHealth());
    }

    void weightCheck(int expected, ICharacter testCharacter) {
        assertEquals(expected, testCharacter.getWeight());
    }

    void attackCheck(int expected, ICharacter testCharacter) {
        assertEquals(expected, testCharacter.getAttack());
    }

    void checkDeadDontEquip(IPlayerCharacter dead) {
        for (Map.Entry<String, IWeapon> entry : weapons.entrySet()) {
            dead.equip(entry.getValue());
            assertNull(dead.getEquippedWeapon());
        }
    }
}
