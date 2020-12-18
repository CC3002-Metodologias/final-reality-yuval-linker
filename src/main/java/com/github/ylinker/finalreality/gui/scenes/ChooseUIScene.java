package com.github.ylinker.finalreality.gui.scenes;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.model.character.ICharacter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.util.*;

/**
 * The class that controls the scene where the player chooses the roster
 */
public class ChooseUIScene {
    private GameController controller;
    private final List<String> classes;
    private Random random;
    private TextArea nameArea;
    private ChooseInventoryScene nextScene;
    private Stage primaryStage;
    private HashMap<ICharacter, String> playerClasses;
    private int i = 0;

    /**
     * Creates the choos UI Scene controller/builder
     * @param controller
     *      The game controller
     * @param primaryStage
     *      The application controller
     * @param nextScene
     *      The next scene (Choos inventory scene)
     */
    public ChooseUIScene(GameController controller, Stage primaryStage, ChooseInventoryScene nextScene) {
        this.controller = controller;
        classes = Arrays.asList("Knight", "Engineer", "Black Mage", "White Mage", "Thief");
        Random seed = new Random();
        random = new Random(seed.nextInt());
        this.nextScene = nextScene;
        this.primaryStage = primaryStage;
        playerClasses = new HashMap<>();
    }

    private void changeScene() throws FileNotFoundException {
        nextScene.setPlayerClasses(playerClasses);
        Scene next = nextScene.build();
        primaryStage.setScene(next);
    }

    private TextArea makeTextArea(int width, int height) {
        TextArea nameArea = new TextArea();
        nameArea.setText("Put your character's name here!");
        nameArea.setPrefWidth(width);
        nameArea.setPrefHeight(height);
        this.nameArea = nameArea;
        return nameArea;
    }

    private Text makeTitle() {
        Text title = new Text("Final Reality!");
        title.setFont(Font.font("suruma", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setWrappingWidth(1280);
        title.setTextAlignment(TextAlignment.CENTER);
        title.minHeight(200);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3);
        ds.setOffsetX(2);
        title.setEffect(ds);
        return title;
    }

    private Text makeInstructions(String instructions) {
        Text text = new Text(instructions);
        text.setFont(Font.font("Times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setWrappingWidth(1280);
        text.setTextAlignment(TextAlignment.CENTER);
        text.minHeight(200);
        return text;
    }

    private VBox Top() {
        VBox top = new VBox();
        top.setSpacing(10);
        top.getChildren().add(makeTitle());
        top.getChildren().add(makeInstructions("Write a name for your character and press on " +
                "a button to choose a class."));
        top.getChildren().add(makeInstructions("Choose 5 characters to start playing!"));
        top.setPadding(new Insets(50, 0, 0, 0));
        return top;
    }

    private FlowPane makeButtons(int buttonWidth, int buttonHeight) {
        FlowPane buttons = new FlowPane();
        buttons.setHgap(200);
        buttons.setVgap(10);
        for(String c: classes) {
            Button button = new Button(c);
            buttons.getChildren().add(button);
            button.setPrefSize(buttonWidth, buttonHeight);
            switch (c) {
                case "Knight":
                    button.setOnAction((event -> {
                        controller.createKnight(
                                nameArea.getText(),
                                random.nextInt(40) + 20,
                                random.nextInt(20) + 10,
                                random.nextInt(13) + 5
                        );
                        nameArea.clear();
                        playerClasses.put(controller.getCharacters().get(i), c);
                        i++;
                        if(controller.getCharacters().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
                case "Engineer":
                    button.setOnAction((event -> {
                        controller.createEngineer(
                                nameArea.getText(),
                                random.nextInt(40) + 20,
                                random.nextInt(20) + 10,
                                random.nextInt(13) + 5
                        );
                        nameArea.clear();
                        playerClasses.put(controller.getCharacters().get(i), c);
                        i++;
                        if(controller.getCharacters().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
                case "Thief":
                    button.setOnAction((event -> {
                        controller.createThief(
                            nameArea.getText(),
                            random.nextInt(40) + 20,
                            random.nextInt(20) + 10,
                            random.nextInt(13) + 5
                        );
                        nameArea.clear();
                        playerClasses.put(controller.getCharacters().get(i), c);
                        i++;
                        if(controller.getCharacters().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));

                    break;
                case "Black Mage":
                    button.setOnAction((event -> {
                        controller.createBlackMage(
                                nameArea.getText(),
                                random.nextInt(40) + 20,
                                random.nextInt(20) + 10,
                                random.nextInt(13) + 5,
                                0
                        );
                        nameArea.clear();
                        playerClasses.put(controller.getCharacters().get(i), c);
                        i++;
                        if(controller.getCharacters().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
                case "White Mage":
                    button.setOnAction((event -> {
                        controller.createWhiteMage(
                                nameArea.getText(),
                                random.nextInt(40) + 20,
                                random.nextInt(20) + 10,
                                random.nextInt(13) + 5,
                                0
                        );
                        nameArea.clear();
                        playerClasses.put(controller.getCharacters().get(i), c);
                        i++;
                        if(controller.getCharacters().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
            }
        }
        return buttons;
    }

    private VBox center() {
        VBox center = new VBox();
        center.getChildren().add(makeTextArea(600, 50));
        center.getChildren().add(makeButtons(200, 100));
        center.setMaxWidth(1000);
        center.setSpacing(50);
        center.setPadding(new Insets(50, 0, 0, 0));
        return center;
    }

    /**
     * Builds the scene where the player chooses the character roster
     * @return
     *      The Choose Character Scene
     */
    public Scene build() {
        BorderPane root = new BorderPane();
        root.setTop(Top());
        root.setCenter(center());
        Scene scene = new Scene(root, 1280, 720);
        return scene;
    }
}
