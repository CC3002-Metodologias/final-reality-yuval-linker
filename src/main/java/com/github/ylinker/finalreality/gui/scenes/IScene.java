package com.github.ylinker.finalreality.gui.scenes;

import com.github.ylinker.finalreality.model.character.ICharacter;

import java.io.FileNotFoundException;

public interface IScene {
    public void playerTurn(ICharacter character) throws FileNotFoundException;
    public void enemyTurn(ICharacter character) throws FileNotFoundException;
    public void winScene();
    public void loseScene();
}
