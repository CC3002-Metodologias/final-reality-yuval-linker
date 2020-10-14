package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testCommon = new Enemy(turns, ENEMY_NAME, HEALTH, ATTACK, DEFENSE, 10);
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(turns, ENEMY_NAME, 50, 10, 20, 10),
        testCommon,
        new Enemy(turns, ENEMY_NAME, 50, 10, 20, 20),
        new Thief(turns, ENEMY_NAME, 50, 10, 20));
    assertNotEquals(new Enemy(turns, "Test", 50, 10, 20, 10), testCommon);
  }
}