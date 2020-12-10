package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;

public class SelectActionPhase extends Phase {

    @Override
    public void toSelectAttackingTargetPhase() {
        SelectAttackingTargetPhase phase = new SelectAttackingTargetPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }

    @Override
    public void toSelectWeaponPhase() {
        SelectWeaponPhase phase = new SelectWeaponPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }
}
