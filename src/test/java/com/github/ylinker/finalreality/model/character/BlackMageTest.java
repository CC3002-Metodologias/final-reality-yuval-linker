package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlackMageTest extends AbstractMageTest {

    private static final String BLACK_MAGE_NAME = "Dark Magician";

    @BeforeEach
    void blackMageSetUp() {
        setUp();
        testMage = new BlackMage(inventory, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testCommon = new BlackMage(inventory, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testPlayer = new BlackMage(inventory, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testDead = new BlackMage(inventory, BLACK_MAGE_NAME, 0, ATTACK, DEFENSE, MAGE_MANA);
    }

    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(inventory, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                (ICharacter) testMage,
                new BlackMage(inventory, "Test", HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                new Knight(inventory, "Arthur", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(testMage, testEnemy);
        assertEquals(new BlackMage(inventory, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, 5), testMage);
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
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
        assertFalse(inventory.contains(weapons.get("staff")));
        // Equip Axe
        inventory.add(weapons.get("axe"));
        testPlayer.equip(weapons.get("axe"));
        assertEquals(weapons.get("staff"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("staff").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("staff").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("axe")));
        assertFalse(inventory.contains(weapons.get("staff")));
        // Equip Knife
        inventory.add(weapons.get("knife"));
        testPlayer.equip(weapons.get("knife"));
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("staff")));
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
        assertEquals(weapons.get("knife"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("knife").getDamage(), testPlayer);
        weightCheck(10 + weapons.get("knife").getWeight(), testPlayer);
        assertTrue(inventory.contains(weapons.get("sword")));
        assertFalse(inventory.contains(weapons.get("knife")));
    }

    @Test
    void deadTest() {
        checkDeadDontAttack(testEnemy);
        checkDeadDontEquip((IPlayerCharacter) testDead);
    }
}
