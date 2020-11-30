package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest extends AbstractPlayerTest {

    private static final String KNIGHT_NAME = "Arthur";

    @BeforeEach
    void knightSetUp() {
        setUp();
        testCommon = new Knight(inventory, KNIGHT_NAME, HEALTH, ATTACK, DEFENSE);
        testPlayer = new Knight(inventory, KNIGHT_NAME, HEALTH, ATTACK, DEFENSE);
        testDead = new Knight(inventory, KNIGHT_NAME, 0, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(inventory, KNIGHT_NAME, HEALTH, ATTACK, DEFENSE),
                testCommon,
                new Knight(inventory, "Test", HEALTH, ATTACK, DEFENSE),
                new Engineer(inventory, "Tesla", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(testCommon, testEnemy);
    }

    @Test
    void equipWeaponTest() {
        // Default Nothing equipped
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, testPlayer);
        weightCheck(10, testPlayer);
        // Equip Staff
        inventory.add(weapons.get("staff"));
        testPlayer.equip(weapons.get("staff"));
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, testPlayer);
        weightCheck(10, testPlayer);
        assertTrue(inventory.contains(weapons.get("staff")));
        // Equip Axe
        inventory.add(weapons.get("axe"));
        testPlayer.equip(weapons.get("axe"));
        assertEquals(weapons.get("axe"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("axe").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("axe").getWeight(), testPlayer);
        assertFalse(inventory.contains(weapons.get("axe")));
        // Equip Knife
        inventory.add(weapons.get("knife"));
        testPlayer.equip(weapons.get("knife"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("axe")));
        assertFalse(inventory.contains(weapons.get("knife")));
        // Equip Bow
        inventory.add(weapons.get("bow"));
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("bow")));
        assertFalse(inventory.contains(weapons.get("knife")));
        // Equip Sword
        inventory.add(weapons.get("sword"));
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("sword"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("sword").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("sword").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("knife")));
        assertFalse(inventory.contains(weapons.get("sword")));
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
