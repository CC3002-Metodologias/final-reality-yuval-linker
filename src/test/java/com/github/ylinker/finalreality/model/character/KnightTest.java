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
        testCommon = new Knight(turns, KNIGHT_NAME, HEALTH, ATTACK, DEFENSE);
        testPlayer = new Knight(turns, KNIGHT_NAME, HEALTH, ATTACK, DEFENSE);
        testDead = new Knight(turns, KNIGHT_NAME, 0, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(turns, KNIGHT_NAME, HEALTH, ATTACK, DEFENSE),
                (ICharacter) testCommon,
                new Knight( turns, "Test", HEALTH, ATTACK, DEFENSE),
                new Engineer(turns, "Tesla", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(testCommon, testEnemy);
    }

    @Test
    void equipWeaponTest() {
        // Default Nothing equipped
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, (ICharacter) testPlayer);
        weightCheck(10, (ICharacter) testPlayer);
        // Equip Staff
        testPlayer.equip(weapons.get("staff"));
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, (ICharacter) testPlayer);
        weightCheck(10, (ICharacter) testPlayer);
        // Equip Axe
        testPlayer.equip(weapons.get("axe"));
        assertEquals(weapons.get("axe"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("axe").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("axe").getWeight(), (ICharacter) testPlayer);
        // Equip Knife
        testPlayer.equip(weapons.get("knife"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), (ICharacter) testPlayer);
        // Equip Bow
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), (ICharacter) testPlayer);
        // Equip Sword
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("sword"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("sword").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("sword").getWeight(), (ICharacter) testPlayer);
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
