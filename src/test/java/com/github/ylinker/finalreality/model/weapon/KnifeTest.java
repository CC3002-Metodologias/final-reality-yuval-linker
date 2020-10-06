package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnifeTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp() {
        testWeapon = new Knife("knifeTest", DAMAGE, SPEED, WEIGHT);
    }

    @Test
    void constructorTest() {
            checkConstruction(new Knife("knifeTest", DAMAGE, SPEED, WEIGHT),
                    testWeapon,
                    new Knife("Test", DAMAGE, SPEED, WEIGHT),
                    new Sword("sword", DAMAGE, SPEED, WEIGHT));
    }
}
