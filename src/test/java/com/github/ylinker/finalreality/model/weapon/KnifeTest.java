package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class KnifeTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp() {
        testWeapon = new Knife("knifeTest", DAMAGE, WEIGHT);
    }

    @Test
    void constructorTest() {
            checkConstruction(new Knife("knifeTest", DAMAGE, WEIGHT),
                    testWeapon,
                    new Knife("Test", DAMAGE, WEIGHT),
                    new Knife("knifeTest", DIFF_DAMAGE, WEIGHT),
                    new Knife("knifeTest", DAMAGE, DIFF_WEIGHT),
                    new Sword("sword", DAMAGE, WEIGHT));
    }

    @Test
    void equipTest() {
        assertEquals(testWeapon, testWeapon.equipToKnight());
        assertEquals(testWeapon, testWeapon.equipToThief());
        assertEquals(testWeapon, testWeapon.equipToBlackMage());
        assertNull(testWeapon.equipToThief());
        assertNull(testWeapon.equipToWhiteMage());
    }
}
