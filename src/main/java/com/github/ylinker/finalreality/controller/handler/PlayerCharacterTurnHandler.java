package com.github.ylinker.finalreality.controller.handler;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * The Observer for the event of the player character's turn beginning
 */
public class PlayerCharacterTurnHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Creates the event handler for the player Character's turn
     * @param controller
     *      The game controller
     */
    public PlayerCharacterTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IPlayerCharacter character = (IPlayerCharacter) evt.getNewValue();
        if(controller.getCurrentTurnCharacter() == character) {
            controller.toActionPhase();
            controller.setPhaseCharacter(character);
            controller.turnStarted();
        }
    }
}
