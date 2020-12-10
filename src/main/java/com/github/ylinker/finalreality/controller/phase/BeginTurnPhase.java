package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;

public class BeginTurnPhase extends Phase {

    @Override
    public void toSelectAttackingTargetPhase() {
        changePhase(new SelectAttackingTargetPhase());
    }

    @Override
    public void toSelectActionPhase() {
        changePhase(new SelectActionPhase());
    }

    @Override
    public void beginTurn() {
        controller.beginTurn();
    }
}
