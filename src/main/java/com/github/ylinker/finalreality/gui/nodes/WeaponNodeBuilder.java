package com.github.ylinker.finalreality.gui.nodes;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

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

    public WeaponNodeBuilder setPosition(int hPos, int vPos) {
        this.hPos = hPos;
        this.vPos = vPos;
        return this;
    }

    public WeaponNodeBuilder setSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    public WeaponNodeBuilder setImagePath(String path) {
        this.imagePath = path;
        return this;
    }

    public WeaponNodeBuilder setInfo(String name, int weight, int attack, String className) {
        this.name = name;
        this.weight = weight;
        this.attack = attack;
        this.className = className;
        return this;
    }

    public HBox build() throws FileNotFoundException {
        return new WeaponNode(name, attack, weight, className, hPos, vPos, height, width, imagePath).getNode();
    }
}
