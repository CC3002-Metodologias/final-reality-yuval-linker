package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertFalse(testController.getCharacters().get(0).getScheduledExecutor().isShutdown());
        assertNotNull(testController.getEnemies().get(0).getScheduledExecutor());
        assertFalse(testController.getEnemies().get(0).getScheduledExecutor().isShutdown());
    }

    @Test
    void waitTurnTest() {
        testController.createEngineer("testEngineer", 32, 39, 49);
        testController.createEnemy("testEnemy", 83, 49, 20, 20);
        testController.createKnight("dummy", 22, 43, 43);
        BlockingQueue<ICharacter> queue = testController.getQueue();
        IPlayerCharacter engineer = testController.getCharacters().get(0);
        IPlayerCharacter dummy = testController.getCharacters().get(1);
        Enemy enemy = testController.getEnemies().get(0);
        // Add dummy to queue so that turn doesnt begin instantly
        queue.add(dummy);

        // Schedulers should be null
        assertNull(engineer.getScheduledExecutor());
        assertNull(enemy.getScheduledExecutor());

        testController.waitTurn(enemy);
        testController.waitTurn(engineer);
        assertNotNull(engineer.getScheduledExecutor());
        assertNotNull(enemy.getScheduledExecutor());
        try {
            Thread.sleep(1100);
            assertTrue(engineer.getScheduledExecutor().isShutdown());
            assertFalse(enemy.getScheduledExecutor().isShutdown());
            Thread.sleep(1000);
            assertTrue(enemy.getScheduledExecutor().isShutdown());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEnemyTurn() {
        testController.createEnemy("testEnemy", 20, 20, 10, 10);
        testController.createKnight("testKnight", 20, 10, 10);
        Enemy enemy = testController.getEnemies().get(0);
        IPlayerCharacter knight = testController.getCharacters().get(0);
        testController.waitTurn(enemy);
        try {
            Thread.sleep(500);
            assertEquals(20, testController.getCharacterHealth(knight));
            Thread.sleep(600);
            assertEquals(10, testController.getCharacterHealth(knight));
            Thread.sleep(500);
            assertEquals(10, testController.getCharacterHealth(knight));
            Thread.sleep(600);
            assertEquals(0, testController.getCharacters().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
