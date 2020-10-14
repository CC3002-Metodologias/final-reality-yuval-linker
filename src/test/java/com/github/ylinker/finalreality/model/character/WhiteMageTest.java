package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WhiteMageTest extends AbstractMageTest {

    private static final String WHITE_MAGE_NAME = "Gandalf";

    @BeforeEach
    void whiteMageSetUp() {
        setUp();
        testMage = new WhiteMage(turns, WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testCommon = new WhiteMage(turns, WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                (ICharacter) testMage,
                new WhiteMage( turns, "Test", HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                new Knight(turns, "Arthur", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(this.testMage, testEnemy);
        assertEquals(new WhiteMage(turns, WHITE_MAGE_NAME, HEALTH, ATTACK, DEFENSE, 5), this.testMage);
    }

    @Test
    void weaponTest() {
        equipWeaponCheck((IPlayerCharacter) testMage);
    }
}
