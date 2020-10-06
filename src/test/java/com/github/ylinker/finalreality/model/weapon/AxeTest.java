package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AxeTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp() {
        testWeapon = new Axe("axeTest", DAMAGE, SPEED, WEIGHT);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Axe("knifeTest", DAMAGE, SPEED, WEIGHT),
                testWeapon,
                new Axe("Test", DAMAGE, SPEED, WEIGHT),
                new Sword("sword", DAMAGE, SPEED, WEIGHT));
    }
}
