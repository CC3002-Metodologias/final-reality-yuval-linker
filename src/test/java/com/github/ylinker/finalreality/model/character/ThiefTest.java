package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThiefTest extends AbstractPlayerTest {

    private static final String THIEF_NAME = "Pinera";

    @BeforeEach
    void thiefSetUp() {
        setUp();
        testCommon = new Thief(THIEF_NAME, HEALTH, ATTACK, DEFENSE);
        testPlayer = new Thief(THIEF_NAME, HEALTH, ATTACK, DEFENSE);
        testDead = new Thief(THIEF_NAME, 0, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(THIEF_NAME, HEALTH, ATTACK, DEFENSE),
                (ICharacter) testCommon,
                new Thief("Test", HEALTH, ATTACK, DEFENSE),
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
        // Equip Bow
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(),  testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(),  testPlayer);
        // Equip Knife
        testPlayer.equip(weapons.get("knife"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), testPlayer);
        // Equip Axe
        testPlayer.equip(weapons.get("axe"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), testPlayer);
        // Equip Sword
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("sword"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("sword").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("sword").getWeight(), testPlayer);
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
