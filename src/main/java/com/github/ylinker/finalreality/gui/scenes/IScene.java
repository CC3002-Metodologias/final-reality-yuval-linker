package com.github.ylinker.finalreality.gui.scenes;

import java.io.FileNotFoundException;

/**
 * Interface for a Scene. Mainly created to apply the Null Object Pattern
 * for testing purposes
 */
public interface IScene {
    /**
     * Changes the center of the scene to show the player's turn UI
     * @throws FileNotFoundException
     *      When no image was found
     */
    void playerTurn() throws FileNotFoundException;

    /**
     * Changes the center of the scene to show the enemy's turn UI
     * @throws FileNotFoundException
     *      When no image was found
     */
    void enemyTurn() throws FileNotFoundException;

    /**
     * Changes to the winning screen scene
     */
    void winScene();

    /**
     * Changes to the losing screen scene
     */
    void loseScene();
}
