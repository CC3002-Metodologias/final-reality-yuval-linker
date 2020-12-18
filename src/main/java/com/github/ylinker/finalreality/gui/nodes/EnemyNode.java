package com.github.ylinker.finalreality.gui.nodes;

import com.github.ylinker.finalreality.model.character.Enemy;
import javafx.scene.Scene;
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

/**
 * Class that represents a GUI Node with an image and enemy's info
 */
public class EnemyNode {
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
    private HBox enemyNode;
    private Map<String, Label> labels;

    /**
     * Initializes an Enemy Node
     * @param name
     *      The enemy name
     * @param health
     *      The enemy health
     * @param attack
     *      The enemy attack
     * @param defense
     *      The enemy defense
     * @param hPos
     *      The node horizontal position
     * @param vPos
     *      The node vertical position
     * @param imgHeight
     *      The image's height
     * @param imgWidth
     *      The image's width
     * @param spritePath
     *      The path to the image
     * @throws FileNotFoundException
     *      When the image is not found
     */
    public EnemyNode(@NotNull final String name, final int health, final int attack, final int defense,
                     final int hPos, final int vPos,
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
        labels = new HashMap<>();
        addSprite(spritePath);
        addInfo();
        enemyNode = new HBox();
        enemyNode.setSpacing(8);
        enemyNode.getChildren().add(sprite);
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

    /**
     * Gets the enemy node already built
     * @return
     *      The enemy node
     */
    public HBox getNode() {
        return enemyNode;
    }
}
