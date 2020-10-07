package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StaffTest extends AbstractWeaponTest {

    private final int MAGIC_DAMAGE = 20;
    private final int DIFF_MAGIC_DAMAGE = 12;
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
                new Staff("staffTest", DIFF_DAMAGE, SPEED, WEIGHT, MAGIC_DAMAGE),
                new Staff("staffTest", DAMAGE, DIFF_SPEED, WEIGHT, MAGIC_DAMAGE),
                new Staff("staffTest", DAMAGE, SPEED, DIFF_WEIGHT, MAGIC_DAMAGE),
                new Sword("sword", DAMAGE, SPEED, WEIGHT));
        assertNotEquals(new Staff("staffTest", DAMAGE, SPEED, WEIGHT, DIFF_MAGIC_DAMAGE),
                testWeapon
                );
    }

    @Test
    void magicDamageTest() {
        assertEquals(MAGIC_DAMAGE, testStaff.getMagicDamage());
    }
}
