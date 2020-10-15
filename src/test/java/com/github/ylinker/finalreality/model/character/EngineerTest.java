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
        testCommon = new Engineer(turns, ENGINEER_NAME, HEALTH, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(turns, ENGINEER_NAME, HEALTH, ATTACK, DEFENSE),
                (ICharacter) testCommon,
                new Engineer( turns, "Test", HEALTH, ATTACK, DEFENSE),
                new Knight(turns, "Arthur", HEALTH, ATTACK, DEFENSE));
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
        assertEquals(weapons.get("axe"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("axe").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("axe").getWeight(), (ICharacter) testPlayer);
        // Equip Bow
        testPlayer.equip(weapons.get("bow"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(), (ICharacter) testPlayer);
        // Equip Sword
        testPlayer.equip(weapons.get("sword"));
        assertEquals(weapons.get("bow"), testPlayer.getEquippedWeapon());
        attackCheck(ATTACK + weapons.get("bow").getDamage(), (ICharacter) testPlayer);
        weightCheck(10 + weapons.get("bow").getWeight(), (ICharacter) testPlayer);
    }
}
