package com.github.ylinker.finalreality.gui.nodes;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

/**
 * Class that represents a factory for Player nodes
 */
public class PlayerNodeBuilder {
    private int height;
    private int width;
    private int hPos;
    private int vPos;
    private String name;
    private int health;
    private int defense;
    private int attack;
    private String imagePath;
    private String className;

    /**
     * Sets the node position
     * @param hPos
     *      The horizontal position
     * @param vPos
     *      The vertical position
     * @return
     *      This instance of the player node builder
     */
    public PlayerNodeBuilder setPosition(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
        return this;
    }

    /**
     * Sets the node size
     * @param height
     *      The node's height
     * @param width
     *      The node's width
     * @return
     *      This instance of the player node builder
     */
    public PlayerNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * Sets the node's image path
     * @param path
     *      The image's path
     * @return
     *      This instance of the player node builder
     */
    public PlayerNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    /**
     * Sets the info of the player node
     * @param name
     *      The character's name
     * @param health
     *      The character's health
     * @param attack
     *      The character's attack
     * @param defense
     *      The character's defense
     * @param className
     *      The character's class name
     * @return
     *      This instance of the player node builder
     */
    public PlayerNodeBuilder setInfo(String name, int health, int attack, int defense, String className) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.className = className;
        return this;
    }

    /**
     * Builds the player node
     * @return
     *      The player node created
     * @throws FileNotFoundException
     *      When the image is not found
     */
    public HBox build() throws FileNotFoundException {
        return new PlayerNode(name, health, attack, defense, className, hPos, vPos, height, width, imagePath).getNode();
    }
}