package com.github.ylinker.finalreality.controller.handler;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.controller.handler.IEventHandler;
import com.github.ylinker.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;

/**
 * Observer for the event of an enemy dying
 */
public class EnemyDeadHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Creates the a handler for the enemy's death
     * @param controller
     *      The game controller
     */
    public EnemyDeadHandler(GameController controller) {
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onEnemyDeath((Enemy) evt.getNewValue());
        if (controller.winCondition()) {
            controller.playerWon();
        }
    }
}
