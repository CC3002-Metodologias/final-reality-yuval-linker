package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest extends AbstractWeaponTest {

    @BeforeEach
    void swordSetUp() {
        testWeapon = new Sword("swordTest", DAMAGE, SPEED, WEIGHT);
    }

    @Test
    void constructorTest(){
        checkConstruction(new Sword("swordTest", DAMAGE, SPEED, WEIGHT),
                testWeapon,
                new Sword("Test", DAMAGE, SPEED, WEIGHT),
                new Sword("swordTest", DIFF_DAMAGE, SPEED, WEIGHT),
                new Sword("swordTest", DAMAGE, DIFF_SPEED, WEIGHT),
                new Sword("swordTest", DAMAGE, SPEED, DIFF_WEIGHT),
                new Bow("bow", DAMAGE, SPEED, WEIGHT));
    }
}
