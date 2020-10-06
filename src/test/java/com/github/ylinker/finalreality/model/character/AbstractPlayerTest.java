package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractPlayerTest extends AbstractCharacterTest {

    protected Map<String, IWeapon> weapons;
    protected Enemy testEnemy;

    @BeforeEach
    void setUp() {
        basicSetUp();
        weapons = new HashMap<>();
        weapons.put("axe", new Axe("axeTest", 10, 5, 5));
        weapons.put("bow", new Bow("bowTest", 10, 5, 7));
        testEnemy = new Enemy("testEnemy", 10, turns);
    }

    void equipWeaponCheck(IPlayerCharacter testCharacter) {
        assertEquals(null, testCharacter.getEquippedWeapon());
        weightCheck(10, (ICharacter) testCharacter);
        testCharacter.equip(weapons.get("axe"));
        assertEquals(weapons.get("axe"), testCharacter.getEquippedWeapon());
        weightCheck(weapons.get("axe").getWeight(), (ICharacter) testCharacter);
        testCharacter.equip(weapons.get("bow"));
        assertEquals(weapons.get("bow"), testCharacter.getEquippedWeapon());
        weightCheck(weapons.get("bow").getWeight(), (ICharacter) testCharacter);
    }

    void weightCheck(int expected, ICharacter testCharacter) {
        assertEquals(expected, testCharacter.getWeight());
    }
}
