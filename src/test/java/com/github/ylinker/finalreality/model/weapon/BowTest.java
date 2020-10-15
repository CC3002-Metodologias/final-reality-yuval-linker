package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BowTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp() {
        testWeapon = new Bow("bowTest", DAMAGE, WEIGHT);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Bow("bowTest", DAMAGE, WEIGHT),
                testWeapon,
                new Bow("Test", DAMAGE, WEIGHT),
                new Bow("bowTest", DIFF_DAMAGE, WEIGHT),
                new Bow("bowTest", DAMAGE, DIFF_WEIGHT),
                new Sword("sword", DAMAGE, WEIGHT));
    }

    @Test
    void equipTest() {
        assertEquals(testWeapon, testWeapon.equipToThief());
        assertEquals(testWeapon, testWeapon.equipToEngineer());
        assertNull(testWeapon.equipToBlackMage());
        assertNull(testWeapon.equipToKnight());
        assertNull(testWeapon.equipToWhiteMage());
    }
}
