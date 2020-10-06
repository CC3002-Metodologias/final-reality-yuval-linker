package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractPlayerTest extends AbstractCharacterTest {

    protected Map<String, IWeapon> weapons;

    @BeforeEach
    void setUp() {
        basicSetUp();
        weapons.put("axe", new Axe("axeTest", 10, 5, 5));
        weapons.put("bow", new Bow("bowTest", 10, 5, 5));
        testEnemy = new Enemy("testEnemy", 10, turns);
    }

    @Test
    void equipWeaponTest() {
        assertEquals(null, testMage.getEquippedWeapon());
        testMage.equip(weapons.get("axe"));
        assertEquals(weapons.get("axe"), testMage.getEquippedWeapon());
        testMage.equip(weapons.get("bow"));
        assertEquals(weapons.get("bow"), testMage.getEquippedWeapon());
    }
}
