package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.IMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import com.github.ylinker.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerUtilTest {
    private GameController testController;

    @BeforeEach
    void setUp() {
        testController = new GameController();
    }

    @Test
    void winConditionTest() {
        assertTrue(testController.playerLost());
        assertTrue(testController.playerWon());
        testController.createEnemy("testEnemy", 12, 12, 12, 10);
        assertFalse(testController.playerWon());
        assertTrue(testController.playerLost());
        testController.createEngineer("testCharacter", 12, 12, 12);
        assertFalse(testController.playerLost());
        assertFalse(testController.playerWon());
        testController.getEnemies().clear();
        assertTrue(testController.playerWon());
        assertFalse(testController.playerLost());
    }

    @Test
    void addToQueueTest() {
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("testEnemy", 10, 10, 10, 20);
        BlockingQueue<ICharacter> queue = testController.getQueue();
        assertTrue(queue.isEmpty());
        IPlayerCharacter knight = testController.getCharacters().get(0);
        Enemy enemy = testController.getEnemies().get(0);
        knight.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        enemy.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        testController.addToQueue(knight);
        // On an empty queue the characters should immediately start their turn
        assertFalse(queue.contains(knight));
        assertTrue(queue.isEmpty());
        testController.addToQueue(enemy);
        assertFalse(queue.contains(enemy));
        assertTrue(queue.isEmpty());

        // Add dummy so that queue is not empty
        Enemy dummy = new Enemy("dummy", 1, 1, 1, 10);
        queue.add(dummy);

        knight.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        enemy.setScheduledExecutor(Executors.newSingleThreadScheduledExecutor());
        testController.addToQueue(knight);
        assertTrue(testController.getQueue().contains(knight));
        testController.addToQueue(enemy);
        assertTrue(testController.getQueue().contains(enemy));
    }

    @Test
    void turnInitTest() {
        // Create characters
        testController.createKnight("testKnight", 10, 10, 10);
        testController.createEnemy("testEnemy", 10, 10, 10, 20);
        testController.initTurns();
        assertNotNull(testController.getCharacters().get(0).getScheduledExecutor());
        assertNotNull(testController.getEnemies().get(0).getScheduledExecutor());
    }

}
