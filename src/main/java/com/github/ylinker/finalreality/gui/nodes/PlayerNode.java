package com.github.ylinker.finalreality.gui.nodes;

import com.github.ylinker.finalreality.model.character.Enemy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class PlayerNode {
    private final int imgHeight;
    private final int imgWidth;
    private ImageView sprite;
    private VBox infoNode;
    private int hPos;
    private int vPos;
    private final String name;
    private final int health;
    private final int defense;
    private final int attack;
    private HBox playerNode;
    private Map<String, Label> labels;
    private String charClass;
    private VBox imageNode;

    public PlayerNode(@NotNull final String name, final int health, final int attack, final int defense,
                     @NotNull String charClass, final int hPos, final int vPos,
                     final int imgHeight, final int imgWidth, final String spritePath)
            throws FileNotFoundException {
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.hPos = hPos;
        this.vPos = vPos;
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.attack = attack;
        this.charClass = charClass;
        labels = new HashMap<>();
        addSprite(spritePath);
        imageNode();
        addInfo();
        playerNode = new HBox();
        playerNode.setSpacing(4);
        playerNode.getChildren().add(infoNode);
        playerNode.getChildren().add(imageNode);
    }

    private void addSprite(final String spritePath) throws FileNotFoundException {
        FileInputStream spriteImage = new FileInputStream(spritePath);
        sprite = new ImageView(new Image(spriteImage));
        sprite.setX(hPos);
        sprite.setY(vPos);
        sprite.setFitWidth(imgWidth);
        sprite.setFitHeight(imgHeight);
    }

    private void imageNode() {
        VBox imageNode = new VBox();
        Label className = new Label(charClass);
        className.setStyle("-fx-font-weight: bold");
        className.setAlignment(Pos.BOTTOM_CENTER);
        imageNode.getChildren().add(sprite);
        imageNode.getChildren().add(className);
        this.imageNode = imageNode;
    }

    private void addInfo() {
        infoNode = new VBox();
        infoNode.setSpacing(5);
        infoNode.setMaxWidth(imgHeight);
        Label nameLabel = new Label(name);

        labels.put("name", nameLabel);
        Label healthLabel = new Label("HP: " + health);
        labels.put("health", healthLabel);
        Label attackLabel = new Label("ATK: " + attack);
        labels.put("attack", attackLabel);
        Label defenseLabel = new Label("DEF: " + defense);
        labels.put("defense", defenseLabel);

        infoNode.getChildren().add(nameLabel);
        infoNode.getChildren().add(healthLabel);
        infoNode.getChildren().add(attackLabel);
        infoNode.getChildren().add(defenseLabel);
    }

    public HBox getNode() {
        return playerNode;
    }
}

