package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;
import java.util.Random;

public class EnemyTurnHandler implements IEventHandler{
    private Random random;
    private long seed;
    private final GameController controller;

    public EnemyTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Enemy enemy = (Enemy) evt.getNewValue();
        seed = new Random().nextLong();
        random = new Random(seed);
        int target = random.nextInt(controller.getCharacters().size());
        controller.attack(enemy, controller.getCharacters().get(target));
    }
}
