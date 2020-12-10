package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionPhaseTest extends AbstractPhaseTest {
    @BeforeEach
    void setUp() {
        phase = new SelectActionPhase();
        basicSetUp();
    }

    @Test
    void toBeginningPhaseTest() {
        toBeginningPhase();
    }

    @Test
    void toAttackPhaseTest() throws InvalidTransitionException {
        phase.toSelectAttackingTargetPhase();
        assertEquals(SelectAttackingTargetPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
    }

    @Test
    void toWeaponPhaseTest() throws InvalidTransitionException {
        phase.toSelectWeaponPhase();
        assertEquals(SelectWeaponPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
    }

    @Test
    void toSelectActionPhaseTest() {
        toActionPhase();
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
