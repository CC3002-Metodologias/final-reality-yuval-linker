package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ThiefTest extends AbstractPlayerTest {

    private static final String THIEF_NAME = "Pinera";

    @BeforeEach
    void thiefSetUp() {
        setUp();
        testCommon = new Thief(turns, THIEF_NAME);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(turns, THIEF_NAME),
                (ICharacter) testCommon,
                new Thief( turns, "Test"),
                new Knight(turns, "Arthur"));
        assertNotEquals(testCommon, testEnemy);
    }

    @Test
    void weaponTest() {
        equipWeaponCheck((IPlayerCharacter) testCommon);
    }
}
