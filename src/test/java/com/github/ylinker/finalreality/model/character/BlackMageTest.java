package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BlackMageTest extends AbstractMageTest {

    private static final String BLACK_MAGE_NAME = "Dark Magician";

    @BeforeEach
    void blackMageSetUp() {
        setUp();
        testMage = new BlackMage(turns, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
        testCommon = new BlackMage(turns, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA);
    }

    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                (ICharacter) testMage,
                new BlackMage(turns, "Test", HEALTH, ATTACK, DEFENSE, MAGE_MANA),
                new Knight(turns, "Arthur", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(testMage, testEnemy);
        assertEquals(new BlackMage(turns, BLACK_MAGE_NAME, HEALTH, ATTACK, DEFENSE, 5), testMage);
    }

    @Test
    void weaponTest() {
        equipWeaponCheck((IPlayerCharacter) testMage);
    }
}
