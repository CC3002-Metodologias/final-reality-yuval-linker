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
    void weaponTest() {
        equipWeaponCheck((IPlayerCharacter) testCommon);
    }
}
