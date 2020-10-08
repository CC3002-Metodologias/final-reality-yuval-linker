package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
