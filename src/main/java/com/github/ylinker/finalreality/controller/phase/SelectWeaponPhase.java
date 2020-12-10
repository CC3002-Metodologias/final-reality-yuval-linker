package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;

public class SelectWeaponPhase extends Phase {

    @Override
    public void toSelectActionPhase() {
        SelectActionPhase phase = new SelectActionPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }

    @Override
    public void selectWeapon(IWeapon weapon){
        controller.equip((IPlayerCharacter) character, weapon);
    }
}
