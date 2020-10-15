package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class AbstractPlayerTest extends AbstractCharacterTest {

    protected Map<String, IWeapon> weapons;
    protected Enemy testEnemy;
    protected IPlayerCharacter testPlayer;


    @BeforeEach
    void setUp() {
        basicSetUp();
        weapons = new HashMap<>();
        weapons.put("axe", new Axe("axeTest", 10, 5));
        weapons.put("bow", new Bow("bowTest", 15, 7));
        weapons.put("knife", new Knife("knife", 7, 4));
        weapons.put("staff", new Staff("staff", 5, 17, 30));
        weapons.put("sword", new Sword("sword", 25, 20));
        testEnemy = new Enemy(turns, "testEnemy", 50, 10, 20, 10);
    }


    void weightCheck(int expected, ICharacter testCharacter) {
        assertEquals(expected, testCharacter.getWeight());
    }

    void attackCheck(int expected, ICharacter testCharacter) {
        assertEquals(expected, testCharacter.getAttack());
    }

    void checkDeadDontEquip(IPlayerCharacter dead) {
        for (Map.Entry<String, IWeapon> entry : weapons.entrySet()) {
            dead.equip(entry.getValue());
            assertNull(dead.getEquippedWeapon());
        }
    }
}
