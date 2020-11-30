package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;

public class BeginTurnHandler implements IEventHandler {
    private final GameController controller;

    public BeginTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ICharacter character = (ICharacter) evt.getNewValue();
        // TODO: Make character turn here
    }
}
