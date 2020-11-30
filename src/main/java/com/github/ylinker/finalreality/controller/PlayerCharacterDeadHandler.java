package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * Observer for the event of a Player Character Dying
 */
public class PlayerCharacterDeadHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Creates an Event Handler for the event of a Player Character Dying
     * @param controller
     *      The controller of the game
     */
    public PlayerCharacterDeadHandler(GameController controller) {
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onCharacterDeath((IPlayerCharacter) evt.getNewValue());
    }
}
