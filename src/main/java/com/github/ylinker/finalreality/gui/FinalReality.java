package com.github.ylinker.finalreality.gui;

import com.github.ylinker.finalreality.controller.GameController;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker
 */
public class FinalReality extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private VBox makeColumn(List<String> letters, Text title, Pos position) {
    VBox column = new VBox();
    column.setSpacing(20);
    column.setAlignment(position);
    column.getChildren().add(title);

    for (String enemy: letters) {
      Label enemyName = new Label(enemy);
      enemyName.setFont(new Font(20));
      column.getChildren().add(enemyName);
    }
    return column;
  }

  @Override
  public void start(Stage primaryStage) {
    List<String> letters = Arrays.asList("Alejandra", "Bernardo", "Camila", "Damian", "Esteban", "Francisca");
    GameController controller = new GameController();

    BorderPane root = new BorderPane();

    Text title = new Text("Final Reality!");
    title.setFont(Font.font("suruma", FontWeight.BOLD, FontPosture.REGULAR, 50));
    title.setWrappingWidth(1020);
    title.setTextAlignment(TextAlignment.CENTER);
    title.minHeight(200);
    DropShadow ds = new DropShadow();
    ds.setOffsetY(3);
    ds.setOffsetX(2);
    title.setEffect(ds);

    primaryStage.setTitle("Final reality");

    root.setTop(title);
    BorderPane.setMargin(title, new Insets(20));

    Group main = new Group();
    BorderPane center = new BorderPane();
    center.setPadding(new Insets(0, 0, 600, 0));
    main.getChildren().add(center);
    Label currentTurn = new Label("It's someone's turn");
    currentTurn.setFont(Font.font("suruma", 30));
    currentTurn.setUnderline(true);
    currentTurn.setTextAlignment(TextAlignment.CENTER);
    center.setTop(currentTurn);

    Text enemyTitle = new Text("Enemies");
    enemyTitle.setFont(Font.font("Gubbi", FontWeight.BOLD, 30));
    enemyTitle.setStroke(Color.DARKRED);

    Text playerTitle = new Text("Your \nCharacters");
    playerTitle.setFont(Font.font("Gubbi", FontWeight.BOLD, 30));
    playerTitle.setStroke(Color.DARKBLUE);

    VBox enemies = makeColumn(letters, enemyTitle, Pos.TOP_RIGHT);
    enemies.setPadding(new Insets(50, 0, 0, 50));
    enemies.setStyle("-fx-border-width: 0 2 0 0; " +
            "-fx-border-color: red black green yellow;" +
            "-fx-padding: 0 50 0 10");

    VBox players = makeColumn(letters, playerTitle, Pos.TOP_LEFT);
    players.setPadding(new Insets(0, 50, 0, 0));
    players.setStyle("-fx-border-width: 0 0 0 2; " +
            "-fx-border-color: red black green black;" +
            "-fx-padding: 0 10 0 50");

    root.setLeft(enemies);
    root.setCenter(main);
    root.setRight(players);

    // This sets the size of the Scene to be 400px wide, 200px high
    Scene scene = new Scene(root, 1080, 800);
    primaryStage.setScene(scene);

    primaryStage.show();
  }


}