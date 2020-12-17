package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.model.character.ICharacter;

/**
 * @author Yuval Linker
 * Class that represents the attack phase.
 * In this phase the character chooses who to attack and attacks it.
 * It's always the last phase.
 */
public class SelectAttackingTargetPhase extends Phase {
    /**
     * Transition that goes back to the previous phase.
     * It also passes the current turn's character
     */
    @Override
    public void goBack() {
        SelectActionPhase phase = new SelectActionPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }

    /**
     * Transition to the Begin Turn Phase
     */
    @Override
    public void toBeginTurnPhase() {
        changePhase(new BeginTurnPhase());
    }

    /**
     * Selects the character that is being attacked.
     * It also transitions to the begin turn phase
     * since attacking is always the last action in a turn.
     * @param attacked
     *      The character that is being attacked
     * @return
     */
    @Override
    public int selectTarget(ICharacter attacked) {
        return controller.attack(character, attacked);

    }
}
