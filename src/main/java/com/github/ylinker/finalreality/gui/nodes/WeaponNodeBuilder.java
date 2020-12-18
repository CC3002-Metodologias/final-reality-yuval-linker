package com.github.ylinker.finalreality.gui.nodes;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

/**
 * Class that represents a factory tha makes weapon nodes
 */
public class WeaponNodeBuilder {
    private int height;
    private int width;
    private int hPos;
    private int vPos;
    private String name;
    private int weight;
    private int attack;
    private String imagePath;
    private String className;

    /**
     * Sets the node's position
     * @param hPos
     *      The node's horizontal position
     * @param vPos
     *      The node's vertical position
     * @return
     *      This instance of the weapon node builder
     */
    public WeaponNodeBuilder setPosition(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
        return this;
    }

    /**
     * Sets the weapon node's size
     * @param height
     *      The node's height
     * @param width
     *      The node's width
     * @return
     *      This instance of the weapon node builder
     */
    public WeaponNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * Sets the nodes image path
     * @param path
     *      The path to the image
     * @return
     *      This instance of the weapon node builder
     */
    public WeaponNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    /**
     * Sets the node's info with a weapon info
     * @param name
     *      The weapon's name
     * @param weight
     *      The weapon's weight
     * @param attack
     *      The weapon's damage
     * @param className
     *      The weapon's class name
     * @return
     *      This instance of the weapon node builder
     */
    public WeaponNodeBuilder setInfo(String name, int weight, int attack, String className) {
        this.name = name;
        this.weight = weight;
        this.attack = attack;
        this.className = className;
        return this;
    }

    /**
     * Builds the weapon node
     * @return
     *      The weapon node
     * @throws FileNotFoundException
     *      When the image is not found
     */
    public HBox build() throws FileNotFoundException {
        return new WeaponNode(name, attack, weight, className, hPos, vPos, height, width, imagePath).getNode();
    }
}
