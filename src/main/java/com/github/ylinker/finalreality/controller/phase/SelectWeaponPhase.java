package com.github.ylinker.finalreality.controller.phase;

import com.github.ylinker.finalreality.controller.phase.exceptions.InvalidTransitionException;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;

/**
 * @author Yuval Linker
 * Class that represents the Phase where the player is equipping a weapon.
 * After this phase it goes back to the select action phase.
 */
public class SelectWeaponPhase extends Phase {
    /**
     * Goes back to the previous phase.
     */
    @Override
    public void goBack() {
        toSelectActionPhase();
    }

    /**
     * Transition to the Select Action Phase.
     * It also passes the current turn's character.
     */
    @Override
    public void toSelectActionPhase() {
        SelectActionPhase phase = new SelectActionPhase();
        phase.setCharacter(character);
        changePhase(phase);
    }

    /**
     * Selects a weapon to be equipped by the current turn's character.
     * @param weapon
     *      The weapon that is being equipped
     */
    @Override
    public void selectWeapon(IWeapon weapon){
        controller.equip((IPlayerCharacter) character, weapon);
    }
}
