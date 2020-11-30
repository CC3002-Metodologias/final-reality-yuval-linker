package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Engineer;
import com.github.ylinker.finalreality.model.character.player.common.Knight;
import com.github.ylinker.finalreality.model.character.player.common.Thief;
import com.github.ylinker.finalreality.model.character.player.mage.BlackMage;
import com.github.ylinker.finalreality.model.character.player.mage.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private Map<String, ICharacter> killablePlayerCharacters;
  private Map<String, ICharacter> notKillablePlayerCharacters;
  private Map<String, ICharacter> exactlyKillablePlayerCharacters;
  private Map<String, ICharacter> notDamageablePlayerCharacters;
  private Random random;
  private final int OPPONENT_HEALTH = 15;
  private final int OPPONENT_ATTACK = 0;
  private final int OPPONENT_DEFENSE = 5;

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testCommon = new Enemy(ENEMY_NAME, HEALTH, ATTACK, DEFENSE, 10);
    testDead = new Enemy(ENEMY_NAME, 0, ATTACK, DEFENSE, 10);

    random = new Random();
    killablePlayerCharacters = new HashMap<>();
    notKillablePlayerCharacters = new HashMap<>();
    exactlyKillablePlayerCharacters = new HashMap<>();
    notDamageablePlayerCharacters = new HashMap<>();
    // Player Characters that have less health than 15
    killablePlayerCharacters.put("knight", new Knight("opponent", OPPONENT_HEALTH - (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE));
    killablePlayerCharacters.put("engineer", new Engineer("opponent", OPPONENT_HEALTH - (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE));
    killablePlayerCharacters.put("thief", new Thief("opponent", OPPONENT_HEALTH - (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE));
    killablePlayerCharacters.put("black", new BlackMage("opponent", OPPONENT_HEALTH - (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE, 0));
    killablePlayerCharacters.put("white", new WhiteMage("opponent", OPPONENT_HEALTH - (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE, 0));

    // Player Characters that have exactly 15 health
    exactlyKillablePlayerCharacters.put("knight", new Knight("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE));
    exactlyKillablePlayerCharacters.put("engineer", new Engineer("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE));
    exactlyKillablePlayerCharacters.put("thief", new Thief("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE));
    exactlyKillablePlayerCharacters.put("white", new WhiteMage("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE, 0));
    exactlyKillablePlayerCharacters.put("black", new BlackMage("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE, 0));

    // Player Characters that have more than 15 health
    notKillablePlayerCharacters.put("knight", new Knight("opponent", OPPONENT_HEALTH + (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE + 2));
    notKillablePlayerCharacters.put("engineer", new Engineer("opponent", OPPONENT_HEALTH + (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE + 2));
    notKillablePlayerCharacters.put("thief", new Thief("opponent", OPPONENT_HEALTH + (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE + 2));
    notKillablePlayerCharacters.put("black", new BlackMage("opponent", OPPONENT_HEALTH + (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE + 2, 0));
    notKillablePlayerCharacters.put("white", new WhiteMage("opponent", OPPONENT_HEALTH + (1 + random.nextInt(4)),
            OPPONENT_ATTACK, OPPONENT_DEFENSE + 2, 0));

    // Player Characters that have more armor than the enemy's attack
    notDamageablePlayerCharacters.put("knight", new Knight("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE + (6 + random.nextInt(10))));
    notDamageablePlayerCharacters.put("engineer", new Engineer("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE + (6 + random.nextInt(10))));
    notDamageablePlayerCharacters.put("thief", new Thief("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE + (6 + random.nextInt(10))));
    notDamageablePlayerCharacters.put("white", new WhiteMage("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE + (6 + random.nextInt(10)), 0));
    notDamageablePlayerCharacters.put("black", new BlackMage("opponent", OPPONENT_HEALTH,
            OPPONENT_ATTACK, OPPONENT_DEFENSE + (6 + random.nextInt(10)), 0));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 50, 10, 20, 10),
        testCommon,
        new Enemy(ENEMY_NAME, 50, 10, 20, 20),
        new Thief(ENEMY_NAME, 50, 10, 20));
    assertNotEquals(new Enemy("Test", 50, 10, 20, 10), testCommon);
  }

  @Test
  void deadTest() {
    checkDeadDontAttack(new Knight("defend", 20, 10, 20));
  }

  @Test
  void attackPlayerTest() {
    // Test overkilling every character
    for (Map.Entry<String, ICharacter> entry: killablePlayerCharacters.entrySet()) {
      ICharacter opponent = entry.getValue();
      int health = opponent.getHealth();
      assertTrue(opponent.isAlive());
      assertEquals(5, testCommon.attack(opponent));
      assertEquals(health-5, opponent.getHealth());
      assertTrue(opponent.isAlive());
      assertEquals(5, testCommon.attack(opponent));
      assertEquals(health-10, opponent.getHealth());
      assertTrue(opponent.isAlive());
      assertEquals(5, testCommon.attack(opponent));
      assertEquals(0, opponent.getHealth());
      assertFalse(opponent.isAlive());
    }
    // Test exactly killing every character
    for (Map.Entry<String, ICharacter> entry: exactlyKillablePlayerCharacters.entrySet()) {
      ICharacter opponent = entry.getValue();
      int health = opponent.getHealth();
      assertTrue(opponent.isAlive());
      assertEquals(5, testCommon.attack(opponent));
      assertEquals(health-5, opponent.getHealth());
      assertTrue(opponent.isAlive());
      assertEquals(5, testCommon.attack(opponent));
      assertEquals(health-10, opponent.getHealth());
      assertTrue(opponent.isAlive());
      assertEquals(5, testCommon.attack(opponent));
      assertEquals(0, opponent.getHealth());
      assertFalse(opponent.isAlive());
    }
    // Test not killing any character
    for (Map.Entry<String, ICharacter> entry: notKillablePlayerCharacters.entrySet()) {
      ICharacter opponent = entry.getValue();
      int health = opponent.getHealth();
      assertTrue(opponent.isAlive());
      assertEquals(3, testCommon.attack(opponent));
      assertEquals(health-3, opponent.getHealth());
      assertTrue(opponent.isAlive());
      assertEquals(3, testCommon.attack(opponent));
      assertEquals(health-6, opponent.getHealth());
      assertTrue(opponent.isAlive());
      assertEquals(3, testCommon.attack(opponent));
      assertEquals(health-9, opponent.getHealth());
      assertTrue(opponent.isAlive());
    }
    // Test not doing damage to any character
    for (Map.Entry<String, ICharacter> entry: notDamageablePlayerCharacters.entrySet()) {
      ICharacter opponent = entry.getValue();
      int health = opponent.getHealth();
      assertEquals(0, testCommon.attack(opponent));
      assertEquals(health, opponent.getHealth());
    }
  }
}