package com.github.ylinker.finalreality.gui.nodes;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

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

    public PlayerNodeBuilder setPosition(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
        return this;
    }

    public PlayerNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    public PlayerNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    public PlayerNodeBuilder setInfo(String name, int health, int attack, int defense, String className) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.className = className;
        return this;
    }

    public HBox build() throws FileNotFoundException {
        return new PlayerNode(name, health, attack, defense, className, hPos, vPos, height, width, imagePath).getNode();
    }
}