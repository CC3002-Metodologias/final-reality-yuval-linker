package com.github.ylinker.finalreality.controller;

import com.github.ylinker.finalreality.gui.scenes.IScene;
import com.github.ylinker.finalreality.model.character.ICharacter;

import java.io.FileNotFoundException;

public class NullScene implements IScene {

    @Override
    public void playerTurn(ICharacter character) throws FileNotFoundException {
    }

    @Override
    public void enemyTurn(ICharacter character) throws FileNotFoundException {
    }

    @Override
    public void winScene() {

    }

    @Override
    public void loseScene() {

    }
}
