package com.github.ylinker.finalreality.gui.nodes;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

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

    public EnemyNodeBuilder setPosition(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
        return this;
    }

    public EnemyNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    public EnemyNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    public EnemyNodeBuilder setInfo(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        return this;
    }

    public HBox build() throws FileNotFoundException {
        return new EnemyNode(name, health, attack, defense, hPos, vPos, height, width, imagePath).getNode();
    }
}
