package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidActionException;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import com.github.ylinker.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractPhaseTest {
    protected Phase phase;
    protected GameController controller;
    protected ICharacter dummy;
    protected ICharacter myCharacter;
    protected IWeapon weapon;

    void basicSetUp() {
        controller = new GameController();
        controller.setPhase(phase);
        dummy = new Enemy("dummy", 10, 10, 5, 10);
        myCharacter = new Knight("char", 10, 10, 10);
        controller.createSword("sword", 10, 10);
        weapon = controller.getInventory().get(0);
        phase.setCharacter(myCharacter);
    }

    void toBeginningPhase() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            phase.toBeginTurnPhase();
        });
        String expectedMessage = "Can't change to beginning turn phase";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    void toActionPhase() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            phase.toSelectActionPhase();
        });
        String expectedMessage = "Can't change to select action phase";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    void toAttackPhase() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            phase.toSelectAttackingTargetPhase();
        });
        String expectedMessage = "Can't change to attacking turn phase";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    void toWeaponPhase() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            phase.toSelectWeaponPhase();
        });
        String expectedMessage = "Can't change to equipping weapon phase";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    void selectAttackingTarget() {
        Exception exception = assertThrows(InvalidActionException.class, () -> {
            phase.selectTarget(dummy);
        });

        String expectedMessage = "Can't select a target to attack in this phase";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    void selectWeapon() {
        Exception exception = assertThrows(InvalidActionException.class, () -> {
            phase.selectWeapon(weapon);
        });

        String expectedMessage = "Can't select a weapon to equip in this phase";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
