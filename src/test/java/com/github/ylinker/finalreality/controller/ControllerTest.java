package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private GameController testController;
    private HashMap<String, IPlayerCharacter> playerCharacters;
    private HashMap<String, Enemy> enemies;

    @BeforeEach
    void setUp() {
        testController = new GameController();

    }

    @Test
    void characterCreationTest() {
        testController.createEngineer("engineerTest", 10, 10, 0);
        assertTrue(testController.getCharacters().contains(
                new Engineer(testController.getQueue(), testController.getInventory(), "engineerTest", 10, 10, 0)
                )
        );
        assertEquals(1, testController.getCharacters().size());
        testController.createKnight("knightTest", 10, 10, 25);
        assertTrue(testController.getCharacters().contains(
                new Knight(testController.getQueue(), testController.getInventory(), "knightTest", 10, 10, 25)
                )
        );
        assertEquals(2, testController.getCharacters().size());
        testController.createThief("thiefTest", 5, 20, 5);
        assertTrue(testController.getCharacters().contains(
                new Thief(testController.getQueue(), testController.getInventory(), "thiefTest", 5, 20, 5)
                )
        );
        assertEquals(3, testController.getCharacters().size());
        testController.createWhiteMage("whiteMageTest", 10, 10, 0, 20);
        assertTrue(testController.getCharacters().contains(
                new WhiteMage(testController.getQueue(), testController.getInventory(), "whiteMageTest", 10, 10, 0, 20)
                )
        );
        assertEquals(4, testController.getCharacters().size());
        testController.createBlackMage("blackMageTest", 10, 20, 0, 15);
        assertTrue(testController.getCharacters().contains(
                new BlackMage(testController.getQueue(), testController.getInventory(), "blackMageTest", 10, 20, 0, 15)
                )
        );
        assertEquals(5, testController.getCharacters().size());
        testController.createEnemy("enemyTest", 10, 5, 5, 10);
        assertTrue(testController.getEnemies().contains(
                new Enemy(testController.getQueue(), "enemyTest", 10, 5, 5, 10)
        ));
        assertEquals(1, testController.getEnemies().size());
    }

    @Test
    void characterDeathTest() {
        testController.createEngineer("engineerTest", 10, 10, 0);
        testController.createEnemy("enemyTest", 10, 10, 5, 10);
        Engineer testEngineer = (Engineer) testController.getCharacters().get(0);
        Enemy testEnemy = testController.getEnemies().get(0);

        testController.attack(testEnemy, testEngineer);
        assertFalse(testController.getCharacters().contains(testEngineer));
        assertEquals(0, testController.getCharacters().size());

        testController.createKnight("knightTest", 10, 10, 0);
        testController.createThief("thiefTest", 10, 10, 0);
        Knight testKnight = (Knight) testController.getCharacters().get(0);
        Thief testThief = (Thief) testController.getCharacters().get(1);
        assertEquals(2, testController.getCharacters().size());

        testController.attack(testEnemy, testKnight);
        assertFalse(testController.getCharacters().contains(testKnight));
        assertEquals(1, testController.getCharacters().size());

        testController.createWhiteMage("whiteMageTest", 10, 10, 0, 20);
        testController.createBlackMage("blackMageTest", 10, 10, 0, 15);
        WhiteMage testWhiteMage = (WhiteMage) testController.getCharacters().get(1);
        BlackMage testBlackMage = (BlackMage) testController.getCharacters().get(2);

        testController.attack(testEnemy, testWhiteMage);
        assertFalse(testController.getCharacters().contains(testWhiteMage));
        assertEquals(2, testController.getCharacters().size());

        testController.attack(testEnemy, testThief);
        assertFalse(testController.getCharacters().contains(testThief));
        assertEquals(1, testController.getCharacters().size());

        testController.createEnemy("otherEnemy", 20, 10, 0, 10);
        Enemy otherEnemy = testController.getEnemies().get(1);

        testController.attack(testBlackMage, testEnemy);
        testController.attack(testBlackMage, testEnemy);
        assertFalse(testController.getEnemies().contains(testEnemy));
        assertEquals(1, testController.getEnemies().size());

        testController.attack(otherEnemy, testBlackMage);
        assertFalse(testController.getCharacters().contains(testBlackMage));
        assertEquals(0, testController.getCharacters().size());
        assertEquals(1, testController.getEnemies().size());
    }
}
