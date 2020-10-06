package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffTest extends AbstractWeaponTest {

    private final int MAGIC_DAMAGE = 20;
    private Staff testStaff;

    @BeforeEach
    void setUp() {
        testStaff = new Staff("testStaff", DAMAGE, SPEED, WEIGHT, MAGIC_DAMAGE);
        testWeapon = new Staff("testStaff", DAMAGE, SPEED, WEIGHT, MAGIC_DAMAGE);
    }

    @Test
    void constructorTest(){
        checkConstruction(new Staff("testStaff", DAMAGE, SPEED, WEIGHT, MAGIC_DAMAGE),
                testWeapon,
                new Staff("Test", DAMAGE, SPEED, WEIGHT, MAGIC_DAMAGE),
                new Sword("sword", DAMAGE, SPEED, WEIGHT));
    }

    @Test
    void magicDamageTest() {
        assertEquals(MAGIC_DAMAGE, testStaff.getMagicDamage());
    }
}
