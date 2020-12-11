package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BeginTurnPhaseTest extends AbstractPhaseTest{
    @BeforeEach
    void setUp() {
        phase = new BeginTurnPhase();
        super.basicSetUp();
    }

    @Test
    void goBackTest() {
        goBackError();
    }

    @Test
    void toAttackPhaseTest() throws InvalidTransitionException {
        phase.toSelectAttackingTargetPhase();
        assertEquals(SelectAttackingTargetPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
    }

    @Test
    void toEquipWeaponPhaseTest() {
        toWeaponPhase();
    }

    @Test
    void toSelectActionPhaseTest() throws InvalidTransitionException {
        phase.toSelectActionPhase();
        assertEquals(SelectActionPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
    }

    @Test
    void toBegginingPhaseTest() {
        toBeginningPhase();
    }

    @Test
    void selectTargetTest() {
        selectAttackingTarget();
    }

    @Test
    void selectWeaponTest() {
        selectWeapon();
    }
}
