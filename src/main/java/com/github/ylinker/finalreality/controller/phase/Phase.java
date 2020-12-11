package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidActionException;
import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;

/**
 * @author Yuval Linker
 *
 * Class that represents a generic turn Phase.
 */
public class Phase {
    protected GameController controller;
    protected ICharacter character;

    /**
     * Game Controller setter
     * @param controller
     *      The game controller to be set
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Character setter
     * @param character
     *      The current turn character
     */
    public void setCharacter(ICharacter character) {
        this.character = character;
    }

    /**
     * Game Controller getter
     * @return
     *      This phase's controller
     */
    public GameController getController() {
        return controller;
    }

    /**
     * Character getter
     * @return
     *      The current turn's character.
     */
    public ICharacter getCharacter() {
        return character;
    }

    /**
     * Changes the controller's phase
     * @param phase
     *      The phase that is being transitioned to
     */
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * Transition to Begin Turn Phase
     * @throws InvalidTransitionException
     *      When it is an invalid transition
     */
    public void toBeginTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to beginning turn phase");
    }

    /**
     * Transition to Select Attacking Target Phase
     * @throws InvalidTransitionException
     *      When it is an invalid transition
     */
    public void toSelectAttackingTargetPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to attacking turn phase");
    }

    /**
     * Transition to Select Weapon Phase
     * @throws InvalidTransitionException
     *      When it is an invalid transition
     */
    public void toSelectWeaponPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to equipping weapon phase");
    }

    /**
     * Transition to the Select Action Phase
     * @throws InvalidTransitionException
     *      When it is an invalid transition
     */
    public void toSelectActionPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't change to select action phase");
    }

    /**
     * Selects a character to attack. The attacker is the current turn's character
     * @param character
     *      The character that is being attacked
     * @throws InvalidActionException
     *      When attacking is not a valid action in the current phase
     */
    public void selectTarget(ICharacter character) throws InvalidActionException {
        throw new InvalidActionException("Can't select a target to attack in this phase");
    }

    /**
     * Selects a weapon to equip to the current turn's character
     * @param weapon
     *      The weapon that is being equipped
     * @throws InvalidActionException
     *      When equipping is not a valid action in the current phase
     */
    public void selectWeapon(IWeapon weapon) throws InvalidActionException {
        throw new InvalidActionException("Can't select a weapon to equip in this phase");
    }

    /**
     * Begins a character's turn.
     * If it's not a valid phase to start a turn then it does nothing.
     */
    public void beginTurn() {
    }

    /**
     * Goes back to a previous phase
     * @throws InvalidTransitionException
     *      When going back is not a valid transition
     */
    public void goBack() throws InvalidTransitionException {
        throw new InvalidTransitionException("Can't go to a previous phase");
    }
}
