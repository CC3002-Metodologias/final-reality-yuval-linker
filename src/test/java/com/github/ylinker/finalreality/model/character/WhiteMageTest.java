package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WhiteMageTest extends AbstractMageTest {

    private static final String WHITE_MAGE_NAME = "Gandalf";

    @BeforeEach
    void whiteMageSetUp() {
        setUp();
        testMage = new WhiteMage(WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testCommon = new WhiteMage(WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testPlayer = new WhiteMage(WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testDead = new WhiteMage(WHITE_MAGE_NAME, 0, ATTACK, DEFENSE, MAGE_MANA);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                (ICharacter) testMage,
                new WhiteMage("Test", HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                new Knight("Arthur", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(this.testMage, testEnemy);
        assertEquals(new WhiteMage(WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, 5), this.testMage);
    }

    @Test
    void equipWeaponTest() {
        // Default Nothing equipped
        assertNull(testPlayer.getEquippedWeapon());
        attackCheck(ATTACK, testPlayer);
        weightCheck(10, testPlayer);
        // Equip Staff
        testPlayer.equip(weapons.get("staff"));
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
        // Equip Axe
        testPlayer.equip(weapons.get("axe"));
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
        // Equip Knife
        testPlayer.equip(weapons.get("knife"));
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
        // Equip Bow
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
        // Equip Sword
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
