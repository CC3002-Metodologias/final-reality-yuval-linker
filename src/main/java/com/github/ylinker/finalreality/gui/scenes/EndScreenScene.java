package com.github.ylinker.finalreality.gui.scenes;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class that creates the ending screen scene
 */
public class EndScreenScene {
    private static final String RESOURCE_PATH = "src/main/resources/endScreen.png";

    /**
     * Builds the Victory screen
     * @return
     *      The victory scene
     */
    public Scene buildWinScreen() {
        return getScene("Victory!");
    }

    /**
     * Builds the "You Lose" Screen
     * @return
     *      Returns the losing screen
     */
    public Scene buildLoseScreen() {
        return getScene("You Lose");
    }

    @Nullable
    private Scene getScene(String msg) {
        Group root = new Group();
        BorderPane screen = new BorderPane();
        screen.setPrefSize(1280, 720);
        screen.setMinSize(1280, 720);
        try {
            setBackground(screen);
            Label youWin = makeLabel(msg);
            screen.setCenter(youWin);
            root.getChildren().add(screen);
            Scene scene = new Scene(root, 1280, 720);
            return scene;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private void setBackground(BorderPane pane) throws FileNotFoundException {
        FileInputStream sprite = new FileInputStream(RESOURCE_PATH);
        Image img = new Image(sprite);
        BackgroundSize bSize = new BackgroundSize(1280, 720, false, false, true, true);
        Background background = new Background(new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));
        pane.setBackground(background);
    }

    private Label makeLabel(String msg) {
        Label label = new Label(msg);
        label.setFont(Font.font("Sarai", 250));
        label.setTextFill(Color.GOLDENROD);
        label.setAlignment(Pos.CENTER);
        return label;
    }
}
