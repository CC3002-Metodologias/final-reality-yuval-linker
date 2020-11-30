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
        testCommon = new Engineer(ENGINEER_NAME, HEALTH, ATTACK, DEFENSE);
        testPlayer = new Engineer(ENGINEER_NAME, HEALTH, ATTACK, DEFENSE);
        testDead = new Engineer(ENGINEER_NAME, 0, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(ENGINEER_NAME, HEALTH, ATTACK, DEFENSE),
                testCommon,
                new Engineer("Test", HEALTH, ATTACK, DEFENSE),
                new Knight("Arthur", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(testCommon, testEnemy);
    }

    @Test
    void equipWeaponTest() {
        // Default Nothing equipped
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, testPlayer);
        weightCheck(10, testPlayer);
        // Equip Staff
        testPlayer.equip(weapons.get("staff"));
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, testPlayer);
        weightCheck(10, testPlayer);
        // Equip Axe
        testPlayer.equip(weapons.get("axe"));
        assertEquals(weapons.get("axe"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("axe").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("axe").getWeight(), testPlayer);
        // Equip Knife
        testPlayer.equip(weapons.get("knife"));
        assertEquals(weapons.get("axe"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("axe").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("axe").getWeight(), testPlayer);
        // Equip Bow
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(), testPlayer);
        // Equip Sword
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(), testPlayer);
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
