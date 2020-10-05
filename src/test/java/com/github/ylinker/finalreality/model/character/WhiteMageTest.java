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
        testMage = new WhiteMage(WHITE_MAGE_NAME, turns, MAGE_MANA);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME, turns, 10),
                (ICharacter) testMage,
                new WhiteMage( "Test", turns, 10),
                new Knight(turns, "Arthur"));
        assertNotEquals(this.testMage, testEnemy);
        assertEquals(new WhiteMage(WHITE_MAGE_NAME, turns, 5), this.testMage);
    }
}
