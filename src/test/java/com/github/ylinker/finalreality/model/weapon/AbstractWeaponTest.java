package com.github.ylinker.finalreality.model.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class AbstractWeaponTest {
    protected static final int DAMAGE = 15;
    protected static final int WEIGHT = 5;
    protected static final int DIFF_DAMAGE = 10;
    protected static final int DIFF_WEIGHT = 7;
    protected IWeapon testWeapon;


    protected void checkConstruction(final IWeapon expectedWeapon,
                                     final IWeapon testEqualWeapon,
                                     final IWeapon sameClassDifferentName,
                                     final IWeapon sameClassDifferentDamage,
                                     final IWeapon sameClassDifferentWeight,
                                     final IWeapon differentClassWeapon) {
        assertEquals(testEqualWeapon, testEqualWeapon);
        assertEquals(expectedWeapon, testEqualWeapon);
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
        assertNotEquals(sameClassDifferentName, testEqualWeapon);
        assertNotEquals(sameClassDifferentDamage, testEqualWeapon);
        assertNotEquals(sameClassDifferentWeight, testEqualWeapon);
        assertNotEquals(testEqualWeapon, differentClassWeapon);
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
    }

    @Test
    void damageTest() {
        assertEquals(DAMAGE, testWeapon.getDamage());
    }

    @Test
    void weightTest() {
        assertEquals(WEIGHT, testWeapon.getWeight());
    }
}
