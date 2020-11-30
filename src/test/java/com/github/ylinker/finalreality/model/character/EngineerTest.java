package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineerTest extends AbstractPlayerTest {

    private static final String ENGINEER_NAME = "Tesla";

    @BeforeEach
    void engineerSetUp() {
        setUp();
        testCommon = new Engineer(inventory, ENGINEER_NAME, HEALTH, ATTACK, DEFENSE);
        testPlayer = new Engineer(inventory, ENGINEER_NAME, HEALTH, ATTACK, DEFENSE);
        testDead = new Engineer(inventory, ENGINEER_NAME, 0, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(inventory, ENGINEER_NAME, HEALTH, ATTACK, DEFENSE),
                testCommon,
                new Engineer(inventory, "Test", HEALTH, ATTACK, DEFENSE),
                new Knight(inventory, "Arthur", HEALTH, ATTACK, DEFENSE));
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
        assertEquals(weapons.get("axe"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("axe").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("axe").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("knife")));
        assertFalse(inventory.contains(weapons.get("axe")));
        // Equip Bow
        inventory.add(weapons.get("bow"));
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(), testPlayer);
        assertFalse(inventory.contains(weapons.get("bow")));
        assertTrue(inventory.contains(weapons.get("axe")));
        // Equip Sword
        inventory.add(weapons.get("sword"));
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("sword")));
        assertFalse(inventory.contains(weapons.get("bow")));
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
