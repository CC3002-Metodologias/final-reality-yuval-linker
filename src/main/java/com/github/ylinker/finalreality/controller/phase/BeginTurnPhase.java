package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;

/**
 * @author Yuval
 * Class that represents the Begin Turn Phase.
 * This is the phase where every character starts its turn.
 * It's also the phase where the controller knows it can poll a character on the turns queue.
 */
public class BeginTurnPhase extends Phase {

    /**
     * Transition to the Select Attacking Target Phase
     */
    @Override
    public void toSelectAttackingTargetPhase() {
        changePhase(new SelectAttackingTargetPhase());
    }

    /**
     * Transition to the Select Action Phase
     */
    @Override
    public void toSelectActionPhase() {
        changePhase(new SelectActionPhase());
    }

    /**
     * Method that starts a character's turn.
     */
    @Override
    public void beginTurn() {
        controller.beginTurn();
    }
}
