package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.character.player.mage.IMage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractMageTest extends AbstractPlayerTest {
    protected IMage testMage;
    protected final int MAGE_MANA = 10;

    @Test
    void manaTest() {
        assertEquals(MAGE_MANA, testMage.getMana());
    }
}
