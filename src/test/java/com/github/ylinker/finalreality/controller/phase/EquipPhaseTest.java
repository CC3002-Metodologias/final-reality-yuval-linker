package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidActionException;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipPhaseTest extends AbstractPhaseTest {
    @BeforeEach
    void setUp() {
        phase = new SelectWeaponPhase();
        basicSetUp();
    }

    @Test
    void goBackTest() throws InvalidTransitionException {
        phase.goBack();
        assertEquals(SelectActionPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
        assertEquals(myCharacter, controller.getPhase().getCharacter());
    }

    @Test
    void toBeginningPhaseTest() {
        toBeginningPhase();
    }

    @Test
    void toAttackingPhaseTest() {
        toAttackPhase();
    }

    @Test
    void toWeaponPhaseTest() {
        toWeaponPhase();
    }

    @Test
    void toActionPhaseTest() throws InvalidTransitionException {
        phase.toSelectActionPhase();
        assertEquals(SelectActionPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
        assertEquals(myCharacter, controller.getPhase().getCharacter());
    }

    @Test
    void selectTargetTest() {
        selectAttackingTarget();
    }

    @Test
    void selectWeaponTest() throws InvalidActionException {
        phase.selectWeapon(weapon);
        assertEquals(weapon, ((IPlayerCharacter) myCharacter).getEquippedWeapon());
    }
}
