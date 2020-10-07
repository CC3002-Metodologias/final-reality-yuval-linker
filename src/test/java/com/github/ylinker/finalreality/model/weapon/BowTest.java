package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp() {
        testWeapon = new Bow("bowTest", DAMAGE, SPEED, WEIGHT);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Bow("bowTest", DAMAGE, SPEED, WEIGHT),
                testWeapon,
                new Bow("Test", DAMAGE, SPEED, WEIGHT),
                new Bow("bowTest", DIFF_DAMAGE, SPEED, WEIGHT),
                new Bow("bowTest", DAMAGE, DIFF_SPEED, WEIGHT),
                new Bow("bowTest", DAMAGE, SPEED, DIFF_WEIGHT),
                new Sword("sword", DAMAGE, SPEED, WEIGHT));
    }
}
