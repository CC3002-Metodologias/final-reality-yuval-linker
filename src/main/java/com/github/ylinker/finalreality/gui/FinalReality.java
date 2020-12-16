package com.github.ylinker.finalreality.gui;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.gui.nodes.EnemyNode;
import com.github.ylinker.finalreality.gui.nodes.EnemyNodeBuilder;
import com.github.ylinker.finalreality.gui.scenes.ChooseUIScene;
import com.github.ylinker.finalreality.gui.scenes.MainScene;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public class FinalReality extends Application {

  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }



  private void initEnemies(GameController controller) {
    Random rand = new Random();
    List<String> names = Arrays.asList("Alejandra", "Bernardo", "Camila", "Damian", "Esteban", "Francisca");
    for (int i = 0; i < 5; i++) {
      int nameIndex = rand.nextInt(names.size());
      int health = rand.nextInt(40) + 10;
      int attack = rand.nextInt(20) + 5;
      int defense = rand.nextInt(10) + 10;
      int weight = rand.nextInt(20) + 10;
      controller.createEnemy(names.get(nameIndex), health, attack, defense, weight);
    }
  }



  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    stage = primaryStage;
    GameController controller = new GameController();
    initEnemies(controller);
    primaryStage.setTitle("Final reality");

    MainScene mainScene = new MainScene(controller);

    ChooseUIScene chooseScene = new ChooseUIScene(controller, stage, mainScene);
    primaryStage.setScene(chooseScene.build());

    primaryStage.show();
  }


}