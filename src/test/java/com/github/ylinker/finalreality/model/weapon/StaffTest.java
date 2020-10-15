package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StaffTest extends AbstractWeaponTest {

    private final int MAGIC_DAMAGE = 20;
    private final int DIFF_MAGIC_DAMAGE = 12;
    private Staff testStaff;

    @BeforeEach
    void setUp() {
        testStaff = new Staff("testStaff", DAMAGE, WEIGHT, MAGIC_DAMAGE);
        testWeapon = new Staff("testStaff", DAMAGE, WEIGHT, MAGIC_DAMAGE);
    }

    @Test
    void constructorTest(){
        checkConstruction(new Staff("testStaff", DAMAGE, WEIGHT, MAGIC_DAMAGE),
                testWeapon,
                new Staff("Test", DAMAGE, WEIGHT, MAGIC_DAMAGE),
                new Staff("staffTest", DIFF_DAMAGE, WEIGHT, MAGIC_DAMAGE),
                new Staff("staffTest", DAMAGE, DIFF_WEIGHT, MAGIC_DAMAGE),
                new Sword("sword", DAMAGE, WEIGHT));
        assertNotEquals(new Staff("staffTest", DAMAGE, WEIGHT, DIFF_MAGIC_DAMAGE),
                testWeapon
                );
    }

    @Test
    void magicDamageTest() {
        assertEquals(MAGIC_DAMAGE, testStaff.getMagicDamage());
    }

    @Test
    void equipTest() {
        assertEquals(testWeapon, testWeapon.equipToWhiteMage());
        assertEquals(testWeapon, testWeapon.equipToBlackMage());
        assertNull(testWeapon.equipToKnight());
        assertNull(testWeapon.equipToThief());
        assertNull(testWeapon.equipToEngineer());
    }
}
