package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EngineerTest extends AbstractPlayerTest {

    private static final String ENGINEER_NAME = "Tesla";

    @BeforeEach
    void engineerSetUp() {
        setUp();
        testCommon = new Engineer(turns, ENGINEER_NAME);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(turns, ENGINEER_NAME),
                (ICharacter) testCommon,
                new Engineer( turns, "Test"),
                new Knight(turns, "Arthur"));
        assertNotEquals(testCommon, testEnemy);
    }

    @Test
    void weaponTest() {
        equipWeaponCheck((IPlayerCharacter) testCommon);
    }
}
