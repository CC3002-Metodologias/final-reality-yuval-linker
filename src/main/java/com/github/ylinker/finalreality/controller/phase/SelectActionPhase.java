package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;

/**
 * @author Yuval
 * Class that represents the turn phase where the player decides the action to make.
 */
public class SelectActionPhase extends Phase {
    /**
     * Transition to Select Attacking Target.
     * It also passes the current turn's character
     */
    @Override
    public void toSelectAttackingTargetPhase() {
        SelectAttackingTargetPhase phase = new SelectAttackingTargetPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }

    /**
     * Transition to Select Weapon Phase
     * It also passes the current turn's character
     */
    @Override
    public void toSelectWeaponPhase() {
        SelectWeaponPhase phase = new SelectWeaponPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }
}
