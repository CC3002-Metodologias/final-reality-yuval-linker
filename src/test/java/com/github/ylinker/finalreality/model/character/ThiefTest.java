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
        testCommon = new Thief(turns, THIEF_NAME, HEALTH, ATTACK, DEFENSE);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(turns, THIEF_NAME, HEALTH, ATTACK, DEFENSE),
                (ICharacter) testCommon,
                new Thief( turns, "Test", HEALTH, ATTACK, DEFENSE),
                new Knight(turns, "Arthur", HEALTH, ATTACK, DEFENSE));
        assertNotEquals(testCommon, testEnemy);
    }

    @Test
    void weaponTest() {
        equipWeaponCheck((IPlayerCharacter) testCommon);
    }
}
