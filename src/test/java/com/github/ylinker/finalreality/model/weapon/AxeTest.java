package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AxeTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp() {
        testWeapon = new Axe("axeTest", DAMAGE, WEIGHT);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Axe("axeTest", DAMAGE, WEIGHT),
                testWeapon,
                new Axe("Test", DAMAGE, WEIGHT),
                new Axe("axeTest", DIFF_DAMAGE, WEIGHT),
                new Axe("axeTest", DAMAGE, DIFF_WEIGHT),
                new Sword("sword", DAMAGE, WEIGHT));
    }

    @Test
    void equipTest() {
        assertEquals(testWeapon, testWeapon.equipToKnight());
        assertEquals(testWeapon, testWeapon.equipToEngineer());
        assertNull(testWeapon.equipToBlackMage());
        assertNull(testWeapon.equipToThief());
        assertNull(testWeapon.equipToWhiteMage());
    }
}
