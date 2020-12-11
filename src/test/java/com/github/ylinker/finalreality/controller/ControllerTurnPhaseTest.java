package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.controller.phase.BeginTurnPhase;
import com.github.ylinker.finalreality.controller.phase.SelectActionPhase;
import com.github.ylinker.finalreality.controller.phase.SelectAttackingTargetPhase;
import com.github.ylinker.finalreality.controller.phase.SelectWeaponPhase;
import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTurnPhaseTest {
    private GameController testController;
    private Enemy enemy;
    private IPlayerCharacter knight;
    private IWeapon sword;

    @BeforeEach
    void setUp() {
        testController = new GameController();
        testController.createEnemy("enemy", 25, 10, 5, 10);
        enemy = testController.getEnemies().get(0);
        testController.createKnight("knight", 15, 10, 5);
        knight = testController.getCharacters().get(0);
        testController.createSword("sword", 10, 10);
        sword = testController.getInventory().get(0);
    }

    @Test
    void invalidWeaponPhaseTest() {
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        testController.toEquipPhase();
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        testController.setPhase(new SelectAttackingTargetPhase());
        testController.toEquipPhase();
        assertEquals(SelectAttackingTargetPhase.class, testController.getPhase().getClass());
    }

    @Test
    void invalidAttackPhaseTest() {
        testController.setPhase(new SelectWeaponPhase());
        assertEquals(SelectWeaponPhase.class, testController.getPhase().getClass());
        testController.toAttackPhase();
        assertEquals(SelectWeaponPhase.class, testController.getPhase().getClass());
    }

    @Test
    void invalidGoBackTest() {
        // Try to go back on begin turn
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        testController.goBack();
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        // Try to go back on select action
        testController.setPhase(new SelectActionPhase());
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        testController.goBack();
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
    }

    @Test
    void validGoBackTest() {
        // Go back on select attacking target
        testController.setPhase(new SelectAttackingTargetPhase());
        testController.goBack();
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        // Go back on select weapon
        testController.setPhase(new SelectWeaponPhase());
        testController.goBack();
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
    }

    @Test
    void invalidAttackActionTest() {
        // Try to attack on Begin Turn
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        testController.setPhaseCharacter(enemy);
        testController.tryToAttack(knight);
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        assertEquals(15, knight.getHealth());
        testController.setPhaseCharacter(knight);
        testController.tryToAttack(enemy);
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        assertEquals(25, enemy.getHealth());
        // Try to attack on select weapon phase
        testController.setPhase(new SelectWeaponPhase());
        testController.setPhaseCharacter(enemy);
        testController.tryToAttack(knight);
        assertEquals(SelectWeaponPhase.class, testController.getPhase().getClass());
        assertEquals(15, knight.getHealth());
        testController.setPhaseCharacter(knight);
        testController.tryToAttack(enemy);
        assertEquals(SelectWeaponPhase.class, testController.getPhase().getClass());
        assertEquals(25, enemy.getHealth());
        // Try to attack on select action phase
        testController.setPhase(new SelectActionPhase());
        testController.setPhaseCharacter(enemy);
        testController.tryToAttack(knight);
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        assertEquals(15, knight.getHealth());
        testController.setPhaseCharacter(knight);
        testController.tryToAttack(enemy);
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        assertEquals(25, enemy.getHealth());
    }

    @Test
    void validAttackActionTest() {
        // Knight attacks
        testController.setPhase(new SelectAttackingTargetPhase());
        testController.getQueue().add(knight);
        testController.setPhaseCharacter(knight);
        testController.tryToAttack(enemy);
        assertEquals(20, enemy.getHealth());
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        assertNotNull(knight.getScheduledExecutor());
        assertTrue(testController.getQueue().isEmpty());
        // Enemy attacks
        testController.setPhase(new SelectAttackingTargetPhase());
        testController.getQueue().add(enemy);
        testController.setPhaseCharacter(enemy);
        testController.tryToAttack(knight);
        assertEquals(10, knight.getHealth());
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        assertNotNull(enemy.getScheduledExecutor());
        assertTrue(testController.getQueue().isEmpty());
    }

    @Test
    void invalidEquipActionTest() {
        // Try to equip on Begin Turn
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        testController.setPhaseCharacter(knight);
        testController.tryToEquip(sword);
        assertEquals(BeginTurnPhase.class, testController.getPhase().getClass());
        assertNull(knight.getEquippedWeapon());
        // Try to attack on select target phase
        testController.setPhase(new SelectAttackingTargetPhase());
        assertEquals(SelectAttackingTargetPhase.class, testController.getPhase().getClass());
        testController.setPhaseCharacter(knight);
        testController.tryToEquip(sword);
        assertEquals(SelectAttackingTargetPhase.class, testController.getPhase().getClass());
        assertNull(knight.getEquippedWeapon());
        // Try to attack on select action phase
        testController.setPhase(new SelectActionPhase());
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        testController.setPhaseCharacter(knight);
        testController.tryToEquip(sword);
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        assertNull(knight.getEquippedWeapon());
    }

    @Test
    void validEquipActionTest() {
        testController.setPhase(new SelectWeaponPhase());
        testController.setPhaseCharacter(knight);
        testController.tryToEquip(sword);
        assertEquals(SelectActionPhase.class, testController.getPhase().getClass());
        assertEquals(sword, knight.getEquippedWeapon());
    }
}
