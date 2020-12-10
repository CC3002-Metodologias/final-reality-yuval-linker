package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.model.character.ICharacter;

public class SelectAttackingTargetPhase extends Phase {
    @Override
    public void toBeginTurnPhase() {
        changePhase(new BeginTurnPhase());
    }

    @Override
    public void selectTarget(ICharacter attacked) {
        controller.attack(character, attacked);
        toBeginTurnPhase();
    }
}
