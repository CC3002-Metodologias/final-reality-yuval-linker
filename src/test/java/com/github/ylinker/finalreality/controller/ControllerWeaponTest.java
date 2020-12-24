package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerWeaponTest {
    private GameController testController;

    @BeforeEach
    void setUp() {
        testController = new GameController();
    }

    @Test
    void weaponsTest() {
        // Characters to test equip
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createWhiteMage("testWhiteMage", 10, 10, 10, 5);
        testController.createThief("testThief", 10, 10, 10);
        IPlayerCharacter knight = testController.getCharacters().get(0);
        IPlayerCharacter mage = testController.getCharacters().get(1);
        IPlayerCharacter thief = testController.getCharacters().get(2);

        // Create weapons
        testController.createAxe("testAxe", 10, 5);
        assertEquals(1, testController.getInventory().size());
        IWeapon axe = testController.getInventory().get(0);
        assertTrue(testController.getInventory().contains(axe));
        assertEquals("Axe", testController.getWeaponClass(axe));
        testController.createBow("testAxe", 10, 5);
        assertEquals(2, testController.getInventory().size());
        IWeapon bow = testController.getInventory().get(1);
        assertTrue(testController.getInventory().contains(bow));
        assertEquals("Bow", testController.getWeaponClass(bow));
        testController.createKnife("testAxe", 10, 5);
        assertEquals(3, testController.getInventory().size());
        IWeapon knife = testController.getInventory().get(2);
        assertTrue(testController.getInventory().contains(knife));
        assertEquals("Knife", testController.getWeaponClass(knife));
        testController.createSword("testAxe", 10, 5);
        assertEquals(4, testController.getInventory().size());
        IWeapon sword = testController.getInventory().get(3);
        assertTrue(testController.getInventory().contains(sword));
        assertEquals("Sword", testController.getWeaponClass(sword));
        testController.createStaff("testAxe", 10, 5, 3);
        assertEquals(5, testController.getInventory().size());
        IWeapon staff = testController.getInventory().get(4);
        assertTrue(testController.getInventory().contains(staff));
        assertEquals("Staff", testController.getWeaponClass(staff));

        // Start equipping weapons
        testController.equip(knight, sword);
        assertFalse(testController.getInventory().contains(sword));
        assertEquals(sword, knight.getEquippedWeapon());
        testController.equip(knight, staff);
        assertTrue(testController.getInventory().contains(staff));
        assertEquals(sword, knight.getEquippedWeapon());
        testController.equip(knight, axe);
        assertFalse(testController.getInventory().contains(axe));
        assertTrue(testController.getInventory().contains(sword));
        assertEquals(axe, knight.getEquippedWeapon());
        testController.equip(knight, knife);
        assertFalse(testController.getInventory().contains(knife));
        assertTrue(testController.getInventory().contains(axe));
        assertEquals(knife, knight.getEquippedWeapon());
        testController.equip(knight, bow);
        assertTrue(testController.getInventory().contains(bow));
        assertFalse(testController.getInventory().contains(knife));
        assertEquals(knife, knight.getEquippedWeapon());
        testController.equip(mage, staff);
        assertFalse(testController.getInventory().contains(staff));
        assertEquals(staff, mage.getEquippedWeapon());
        testController.equip(thief, bow);
        assertFalse(testController.getInventory().contains(bow));
        assertEquals(bow, thief.getEquippedWeapon());
    }

    @Test
    void nonInventoryWeaponTest() {
        // Inventory is empty
        // Try equipping weapons with empty inventory
        testController.createKnight("testKnight", 10, 10, 10);
        IPlayerCharacter knight = testController.getCharacters().get(0);
        assertTrue(testController.getInventory().isEmpty());
        testController.equip(knight, new Knife("knife", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());
        testController.equip(knight, new Sword("sword", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());
        testController.equip(knight, new Bow("bow", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());;
        testController.equip(knight, new Staff("staff", 10, 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());;
        testController.equip(knight, new Axe("axe", 10, 10));
        assertTrue(testController.getInventory().isEmpty());
        assertNull(knight.getEquippedWeapon());
        // Start adding weapons but equip only weapons not in inventory
        testController.createKnife("knife", 10, 10);
        testController.equip(knight, new Sword("sword", 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createSword("sword", 10, 10);
        testController.equip(knight, new Axe("axe", 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createAxe("axe", 10, 10);
        testController.equip(knight, new Bow("bow", 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createBow("bow", 10, 10);
        testController.equip(knight, new Staff("staff", 10, 10, 10));
        assertNull(knight.getEquippedWeapon());
        testController.createStaff("staff", 10, 10, 10);
    }

    @Test
    void getWeaponDamageTest() {
        testController.createSword("sword", 5, 10);
        testController.createStaff("staff", 20, 10, 5);
        IWeapon sword = testController.getInventory().get(0);
        IWeapon staff = testController.getInventory().get(1);
        assertEquals(5, testController.getWeaponDamage(sword));
        assertEquals(20, testController.getWeaponDamage(staff));
    }

    @Test
    void getWeaponWeightTest() {
        testController.createSword("sword", 5, 32);
        testController.createStaff("staff", 20, 1, 5);
        IWeapon sword = testController.getInventory().get(0);
        IWeapon staff = testController.getInventory().get(1);
        assertEquals(32, testController.getWeaponWeight(sword));
        assertEquals(1, testController.getWeaponWeight(staff));
    }

    @Test
    void getWeaponNameTest() {
        testController.createSword("sword", 5, 10);
        testController.createStaff("staff", 20, 10, 5);
        IWeapon sword = testController.getInventory().get(0);
        IWeapon staff = testController.getInventory().get(1);
        assertEquals("sword", testController.getWeaponName(sword));
        assertEquals("staff", testController.getWeaponName(staff));
    }

    @Test
    void getStaffMagicDamageTest() {
        testController.createStaff("staff", 1, 2, 20);
        testController.createStaff("staff2", 1, 2, 5);
        Staff staff1 = (Staff) testController.getInventory().get(0);
        Staff staff2 = (Staff) testController.getInventory().get(1);
        assertEquals(20, testController.getStaffMagicDamage(staff1));
        assertEquals(5, testController.getStaffMagicDamage(staff2));
    }
}
