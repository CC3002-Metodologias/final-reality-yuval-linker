package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.gui.scenes.IScene;

import java.io.FileNotFoundException;

public class NullScene implements IScene {

    @Override
    public void playerTurn() throws FileNotFoundException {
    }

    @Override
    public void enemyTurn() throws FileNotFoundException {
    }

    @Override
    public void winScene() {

    }

    @Override
    public void loseScene() {

    }
}
