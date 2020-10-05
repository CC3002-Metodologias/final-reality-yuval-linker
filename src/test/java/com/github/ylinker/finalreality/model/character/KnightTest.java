package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KnightTest extends AbstractPlayerTest {

    private static final String KNIGHT_NAME = "Arthur";

    @BeforeEach
    void knightSetUp() {
        setUp();
        testMage = new Knight(turns, KNIGHT_NAME);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(turns, KNIGHT_NAME),
                (ICharacter) testMage,
                new Knight( turns, "Test"),
                new Engineer(turns, "Tesla"));
        assertNotEquals(testMage, testEnemy);
    }
}
