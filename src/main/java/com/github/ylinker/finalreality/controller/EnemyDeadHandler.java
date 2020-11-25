package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;

public class EnemyDeadHandler implements IEventHandler {
    private GameController controller;

    public EnemyDeadHandler(GameController controller) {
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onEnemyDeath((Enemy) evt.getNewValue());
    }
}
