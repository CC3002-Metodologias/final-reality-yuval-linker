package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class AbstractWeaponTest {
    protected static final int DAMAGE = 15;
    protected static final int SPEED = 10;
    protected static final int WEIGHT = 5;
    protected IWeapon testWeapon;


    protected void checkConstruction(final IWeapon expectedWeapon,
                                     final IWeapon testEqualWeapon,
                                     final IWeapon sameClassDifferentWeapon,
                                     final IWeapon differentClassWeapon) {
        assertEquals(testEqualWeapon, testEqualWeapon);
        assertEquals(expectedWeapon, testEqualWeapon);
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
        assertNotEquals(sameClassDifferentWeapon, testEqualWeapon);
        assertNotEquals(testEqualWeapon, differentClassWeapon);
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
    }

    @Test
    void damageTest() {
        assertEquals(DAMAGE, testWeapon.getDamage());
    }

    @Test
    void speedTest() {
        assertEquals(SPEED, testWeapon.getSpeed());
    }

    @Test
    void weightTest() {
        assertEquals(WEIGHT, testWeapon.getWeight());
    }
}
