package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.common.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testCommon = new Enemy(ENEMY_NAME, 10, turns);
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, turns),
        testCommon,
        new Enemy(ENEMY_NAME, 11, turns),
        new Thief(turns, ENEMY_NAME));
  }
}