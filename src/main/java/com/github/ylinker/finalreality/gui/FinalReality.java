package com.github.ylinker.finalreality.gui;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.gui.scenes.ChooseInventoryScene;
import com.github.ylinker.finalreality.gui.scenes.ChooseUIScene;
import com.github.ylinker.finalreality.gui.scenes.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Main entry point for the application.
 * From this class the application and scenes are started
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public class FinalReality extends Application {

  private Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Initializes the enemies for the game with random stats and random names.
   * @param controller
   *    The game controller
   */
  private void initEnemies(GameController controller) {
    Random rand = new Random();
    List<String> names = Arrays.asList("Drazzadol", "Rag'Dros", "Brogthomoth", "Irthroxir", "Trostras", "Rarran",
            "Drustrun", "Egannen", "Tilmozul", "Sozrith");
    Collections.shuffle(names);
    for (int i = 0; i < 5; i++) {
      int health = rand.nextInt(40) + 10;
      int attack = rand.nextInt(25) + 10;
      int defense = rand.nextInt(10) + 10;
      int weight = rand.nextInt(20) + 10;
      controller.createEnemy(names.get(i), health, attack, defense, weight);
    }
  }

  private void startSound() {
    String path = "src/main/resources/Heroic_Demise.wav";

    try {
      System.out.println("Starting sound");
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
    }
  }

  /**
   * Starts the application.
   * For this 3 scenes are created.
   * @param primaryStage
   *    The application Stage
   */
  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
    GameController controller = new GameController();
    initEnemies(controller);
    primaryStage.setTitle("Final reality");

    MainScene mainScene = new MainScene(controller, primaryStage);
    controller.setScene(mainScene);
    ChooseInventoryScene inventoryScene = new ChooseInventoryScene(controller, primaryStage, mainScene);

    ChooseUIScene chooseScene = new ChooseUIScene(controller, stage, inventoryScene);
    primaryStage.setScene(chooseScene.build());

    startSound();
    primaryStage.show();
  }


}