package com.github.ylinker.finalreality.gui.nodes;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

/**
 * Factory for an enemy node
 */
public class EnemyNodeBuilder {
    private int height;
    private int width;
    private int hPos;
    private int vPos;
    private String name;
    private int health;
    private int defense;
    private int attack;
    private String imagePath;

    /**
     * Sets the position of the enemy node
     * @param hPos
     *      The horizontal position of the node
     * @param vPos
     *      The vertical position of the node
     * @return
     *      This instance of the enemy node builder
     */
    public EnemyNodeBuilder setPosition(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
        return this;
    }

    /**
     * Sets the size of the image of the node
     * @param height
     *      Height of the image
     * @param width
     *      Width of the image
     * @return
     *      This instance of the enemy node builder
     */
    public EnemyNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * Sets the image path of the node's image
     * @param path
     *      The image's path
     * @return
     *      This instance of the enemy node builder
     */
    public EnemyNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    /**
     * Sets the enemy info of the node
     * @param name
     *      The enemy's name
     * @param health
     *      The enemy's health
     * @param attack
     *      The enemy's attack
     * @param defense
     *      The enemy's defense
     * @return
     *      This instance of the enemy node builder
     */
    public EnemyNodeBuilder setInfo(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        return this;
    }

    /**
     * Builds an enemy node
     * @return
     *      The enemy node
     * @throws FileNotFoundException
     *      When the image is not found
     */
    public HBox build() throws FileNotFoundException {
        return new EnemyNode(name, health, attack, defense, hPos, vPos, height, width, imagePath).getNode();
    }
}
