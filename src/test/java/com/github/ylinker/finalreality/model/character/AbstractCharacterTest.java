package com.github.ylinker.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected ICharacter testCommon;
  protected ICharacter testDead;
  protected final int HEALTH = 50;
  protected final int DEFENSE = 20;
  protected final int ATTACK = 10;
  protected ArrayList<IWeapon> inventory;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  /**
   *
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    testCommon.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testCommon, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
   */

  protected void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {
    assertEquals(testEqualCharacter, testEqualCharacter);
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
  }

  protected void basicSetUp() {
    inventory = new ArrayList<>();
  }

  @Test
  protected void testBasicStats() {
    assertEquals(testCommon.getHealth(), 50);
    assertEquals(testCommon.getAttack(), 10);
    assertEquals(testCommon.getDefense(), 20);
  }


  void checkDeadDontAttack(ICharacter other) {
    int health = other.getHealth();
    testDead.attack(other);
    assertEquals(health, other.getHealth());
  }
}
