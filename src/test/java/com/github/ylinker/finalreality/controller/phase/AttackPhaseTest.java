package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidActionException;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackPhaseTest extends AbstractPhaseTest {
    @BeforeEach
    void setUp() {
        phase = new SelectAttackingTargetPhase();
        basicSetUp();
    }

    @Test
    void goBackTest() throws InvalidTransitionException {
        phase.goBack();
        assertEquals(SelectActionPhase.class, controller.getPhase().getClass());
        assertEquals(myCharacter, controller.getPhase().getCharacter());
    }

    @Test
    void toBegginingPhaseTest() throws InvalidTransitionException {
        phase.toBeginTurnPhase();
        assertEquals(BeginTurnPhase.class, controller.getPhase().getClass());
        assertEquals(controller, controller.getPhase().getController());
    }

    @Test
    void toActionPhaseTest() {
        toActionPhase();
    }

    @Test
    void toWeaponPhaseTest() {
        toWeaponPhase();
    }

    @Test
    void toAttackPhaseTest() {
        toAttackPhase();
    }

    @Test
    void selectTargetTest() throws InvalidActionException {
        phase.selectTarget(dummy);
        assertEquals(5, dummy.getHealth());
    }

    @Test
    void selectWeaponTest() {
        selectWeapon();
    }
}
