package com.github.ylinker.finalreality.gui.scenes;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.gui.nodes.EnemyNodeBuilder;
import com.github.ylinker.finalreality.gui.nodes.PlayerNodeBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainScene {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private GameController controller;
    private Label currentTurn;
    private BorderPane center;
    private ArrayList<String> classes;

    public MainScene(GameController controller) {
        this.controller = controller;
    }

    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    public Text makeTitle() {
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

    public Group top(){
        Group top = new Group();
        top.getChildren().add(makeTitle());
        return top;
    }

    private VBox makeColumn(List<HBox> enemies, Text title, Pos position, int spacing) {
        VBox column = new VBox();
        column.setSpacing(spacing);
        column.setAlignment(position);
        column.getChildren().add(title);

        for (var enemy: enemies) {
            column.getChildren().add(enemy);
        }
        return column;
    }

    private ArrayList<HBox> initEnemyNodes() throws FileNotFoundException {
        EnemyNodeBuilder nodeBuilder = new EnemyNodeBuilder();
        nodeBuilder.setImagePath(RESOURCE_PATH + "enemy.png");
        nodeBuilder.setPosition(10, 10);
        nodeBuilder.setSize(75, 90);
        ArrayList<HBox> enemies = new ArrayList<>();
        for (var e: controller.getEnemies()) {
            nodeBuilder.setInfo(controller.getCharacterName(e),
                    controller.getCharacterHealth(e),
                    controller.getCharacterAttack(e),
                    controller.getCharacterDefense(e));
            enemies.add(nodeBuilder.build());
        }
        return enemies;
    }

    private ArrayList<HBox> initPlayerNodes() throws FileNotFoundException {
        PlayerNodeBuilder nodeBuilder = new PlayerNodeBuilder();
        nodeBuilder.setImagePath(RESOURCE_PATH + "enemy.png");
        nodeBuilder.setPosition(10, 10);
        nodeBuilder.setSize(75, 90);
        ArrayList<HBox> playerCharacters = new ArrayList<>();
        int i = 0;
        for (var e: controller.getCharacters()) {
            nodeBuilder.setInfo(controller.getCharacterName(e),
                    controller.getCharacterHealth(e),
                    controller.getCharacterAttack(e),
                    controller.getCharacterDefense(e),
                    classes.get(i));
            playerCharacters.add(nodeBuilder.build());
            i++;
        }
        return playerCharacters;
    }

    private VBox left() throws FileNotFoundException {
        Text enemyTitle = new Text("Enemies");
        enemyTitle.setFont(Font.font("Gubbi", FontWeight.BOLD, 30));
        enemyTitle.setStroke(Color.DARKRED);

        VBox enemies = makeColumn(initEnemyNodes(), enemyTitle, Pos.TOP_RIGHT, 20);
        enemies.setPadding(new Insets(100, 0, 0, 50));
        enemies.setStyle("-fx-border-width: 0 2 0 0; " +
                "-fx-border-color: red black green yellow;" +
                "-fx-padding: 50 50 0 10");
        return enemies;
    }

    private Group center() {
        Group main = new Group();
        center = new BorderPane();
        center.setPadding(new Insets(0, 0, 600, 0));
        main.getChildren().add(center);
        currentTurn = new Label("Waiting for a character's turn to start");
        currentTurn.setFont(Font.font("suruma", 30));
        currentTurn.setUnderline(true);
        currentTurn.setTextAlignment(TextAlignment.CENTER);
        center.setTop(currentTurn);
        return main;
    }

    private VBox right() throws FileNotFoundException {
        Text playerTitle = new Text("Your \nCharacters");
        playerTitle.setFont(Font.font("Gubbi", FontWeight.BOLD, 30));
        playerTitle.setStroke(Color.DARKBLUE);

        VBox players = makeColumn(initPlayerNodes(), playerTitle, Pos.TOP_LEFT, 5);
        players.setPadding(new Insets(0, 50, 0, 0));
        players.setStyle("-fx-border-width: 0 0 0 2; " +
                "-fx-border-color: red black #9fdfea black;" +
                "-fx-padding: 0 40 0 50");
        return players;
    }

    public Scene build() throws FileNotFoundException {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
                Color.color(159/255.0, 223/255.0, 234/255.0, 0.2),
                new CornerRadii(0),
                Insets.EMPTY)));
        root.setTop(top());
        root.setLeft(left());
        root.setRight(right());
        root.setCenter(center());
        return new Scene(root, 1280, 720);
    }
}
