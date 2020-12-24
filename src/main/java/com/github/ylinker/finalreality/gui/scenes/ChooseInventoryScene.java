package com.github.ylinker.finalreality.gui.scenes;

import com.github.ylinker.finalreality.controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class that makes the Scene where the player chooses the weapons for the inventory
 */
public class ChooseInventoryScene {
    private GameController controller;
    private Random random;
    private MainScene nextScene;
    private Stage primaryStage;
    private List<String> classes;
    private List<String> names;
    private int i = 0;

    /**
     * Constructor for the class
     * @param controller
     *      The game controller
     * @param primaryStage
     *      The application stage
     * @param nextScene
     *      The next scene (The Main Scene)
     */
    public ChooseInventoryScene(GameController controller, Stage primaryStage, MainScene nextScene) {
        this.controller = controller;
        classes = Arrays.asList("Knife", "Axe", "Bow", "Staff", "Sword");
        names = Arrays.asList("Dyrnwyn", "Sharur", "Halayudha", "Zulfiqar", "Tyrfing", "Gram", "Kusanagi", "Tonbogiri");
        Collections.shuffle(names);
        Random seed = new Random();
        random = new Random(seed.nextInt());
        this.nextScene = nextScene;
        this.primaryStage = primaryStage;
    }

    private void changeScene() throws FileNotFoundException {
        Scene next = nextScene.build();
        primaryStage.setScene(next);
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
        top.getChildren().add(makeInstructions("Press a button to choose a weapon"));
        top.getChildren().add(makeInstructions("You need to have 5 weapons in your inventory. Choose wisely"));
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
                case "Knife":
                    button.setOnAction((event -> {
                        controller.createKnife(
                                names.get(i),
                                random.nextInt(5) + 15,
                                random.nextInt(10) + 10
                        );
                        i++;
                        if(controller.getInventory().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
                case "Axe":
                    button.setOnAction((event -> {
                        controller.createAxe(
                                names.get(i),
                                random.nextInt(5) + 15,
                                random.nextInt(10) + 10
                        );
                        i++;
                        if(controller.getInventory().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
                case "Sword":
                    button.setOnAction((event -> {
                        controller.createSword(
                                names.get(i),
                                random.nextInt(5) + 15,
                                random.nextInt(10) + 10
                        );
                        i++;
                        if(controller.getInventory().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));

                    break;
                case "Staff":
                    button.setOnAction((event -> {
                        controller.createStaff(
                                names.get(i),
                                random.nextInt(5) + 15,
                                random.nextInt(10) + 10,
                                0
                        );
                        i++;
                        if(controller.getInventory().size() == 5) {
                            try {
                                changeScene();
                            } catch (FileNotFoundException e) {
                            }
                        }
                    }));
                    break;
                case "Bow":
                    button.setOnAction((event -> {
                        controller.createBow(
                                names.get(i),
                                random.nextInt(5) + 15,
                                random.nextInt(10) + 10
                        );
                        i++;
                        if(controller.getInventory().size() == 5) {
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
        center.getChildren().add(makeButtons(200, 100));
        center.setMaxWidth(1000);
        center.setSpacing(50);
        center.setPadding(new Insets(50, 0, 0, 0));
        return center;
    }

    /**
     * Builds the scene where the player chooses the inventory
     * @return
     *      The choose inventory scene
     */
    public Scene build() {
        BorderPane root = new BorderPane();
        root.setTop(Top());
        root.setCenter(center());
        Scene scene = new Scene(root, 1280, 720);
        return scene;
    }
}
