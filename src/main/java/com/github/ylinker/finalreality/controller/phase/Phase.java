package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidActionException;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;

public class Phase {
    protected GameController controller;
    protected ICharacter character;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setCharacter(ICharacter character) {
        this.character = character;
    }

    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    public void toBeginTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to beginning turn phase");
    }

    public void toSelectAttackingTargetPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to attacking turn phase");
    }

    public void toSelectWeaponPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to equipping weapon phase");
    }

    public void toSelectActionPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to select action phase");
    }

    public void selectTarget(ICharacter character) throws InvalidActionException {
        throw new InvalidActionException("Can't select a target to attack in this phase");
    }

    public void selectWeapon(IWeapon weapon) throws InvalidActionException {
        throw new InvalidActionException("Can't select a weapon to equip in this phase");
    }

    public void beginTurn() throws InvalidActionException {
        throw new InvalidActionException("Can't begin a turn in this phase");
    }
}
