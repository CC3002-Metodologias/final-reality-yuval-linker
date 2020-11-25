package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

public class PlayerCharacterDeadHandler implements IEventHandler {
    private GameController controller;

    public PlayerCharacterDeadHandler(GameController controller) {
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onCharacterDeath((IPlayerCharacter) evt.getNewValue());
    }
}
