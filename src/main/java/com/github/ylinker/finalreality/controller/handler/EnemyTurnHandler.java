package com.github.ylinker.finalreality.controller.handler;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.controller.handler.IEventHandler;
import com.github.ylinker.finalreality.controller.phase.SelectAttackingTargetPhase;
import com.github.ylinker.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;
import java.util.Random;

/**
 * Observer for the event of an enemy's turn beginning
 */
public class EnemyTurnHandler implements IEventHandler {
    private Random random;
    private long seed;
    private final GameController controller;

    /**
     * Creates an event handler for the enemy's turn
     * @param controller
     *      The game controller
     */
    public EnemyTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Enemy enemy = (Enemy) evt.getNewValue();

        controller.toAttackPhase();
        controller.setPhaseCharacter(enemy);
        controller.enemyTurnStarted();
    }
}
