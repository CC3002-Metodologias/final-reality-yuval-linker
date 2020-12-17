package com.github.ylinker.finalreality.gui.nodes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class WeaponNode {
    private final int imgHeight;
    private final int imgWidth;
    private ImageView sprite;
    private VBox infoNode;
    private int hPos;
    private int vPos;
    private final String name;
    private final int attack;
    private final int weight;
    private HBox enemyNode;
    private Map<String, Label> labels;
    private VBox imageNode;
    private String charClass;

    public WeaponNode(@NotNull final String name, final int attack, final int weight,
                     String weaponClass, final int hPos, final int vPos,
                     final int imgHeight, final int imgWidth, final String spritePath)
            throws FileNotFoundException {
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.hPos = hPos;
        this.vPos = vPos;
        this.name = name;
        this.weight = weight;
        this.attack = attack;
        this.charClass = weaponClass;
        labels = new HashMap<>();
        addSprite(spritePath);
        imageNode();
        addInfo();
        enemyNode = new HBox();
        enemyNode.setSpacing(8);
        enemyNode.getChildren().add(imageNode);
        enemyNode.getChildren().add(infoNode);
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
        Label attackLabel = new Label("ATK: " + attack);
        labels.put("attack", attackLabel);
        Label weightLabel = new Label("Weight: " + weight);
        labels.put("weight", weightLabel);
        infoNode.getChildren().add(nameLabel);
        infoNode.getChildren().add(weightLabel);
        infoNode.getChildren().add(attackLabel);
    }

    public HBox getNode() {
        return enemyNode;
    }
}
