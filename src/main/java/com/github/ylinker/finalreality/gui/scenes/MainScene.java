package com.github.ylinker.finalreality.gui.scenes;

import com.github.ylinker.finalreality.controller.GameController;
import com.github.ylinker.finalreality.controller.handler.IEventHandler;
import com.github.ylinker.finalreality.gui.nodes.EnemyNodeBuilder;
import com.github.ylinker.finalreality.gui.nodes.PlayerNodeBuilder;
import com.github.ylinker.finalreality.gui.nodes.WeaponNodeBuilder;
import com.github.ylinker.finalreality.model.character.Enemy;
import com.github.ylinker.finalreality.model.character.ICharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainScene implements IScene {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private final GameController controller;
    private final Stage primaryStage;
    private Label currentTurn;
    private BorderPane root;
    private BorderPane center;
    private Group main;
    private HashMap<ICharacter, String> classes;
    private HashMap<IWeapon, String> weapons;
    private EndScreenScene endScreenScene = new EndScreenScene();

    public MainScene(GameController controller, Stage stage) {
        this.controller = controller;
        this.primaryStage = stage;
    }

    public void setClasses(HashMap<ICharacter, String> classes) {
        this.classes = classes;
    }

    public void setWeapons(HashMap<IWeapon, String> weapons) {
        this.weapons = weapons;
    }


    public Text makeTitle() {
        Text title = new Text("Final Reality!");
        title.setFont(Font.font("suruma", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setWrappingWidth(1280);
        title.setTextAlignment(TextAlignment.CENTER);
        title.minHeight(200);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3);
        ds.setOffsetX(2);
        title.setEffect(ds);
        return title;
    }

    public Group top(){
        Group top = new Group();
        top.getChildren().add(makeTitle());
        return top;
    }

    private VBox makeColumn(List<HBox> enemies, Text title, Pos position, int spacing) {
        VBox column = new VBox();
        column.setSpacing(spacing);
        column.setAlignment(position);
        column.getChildren().add(title);

        for (var enemy: enemies) {
            column.getChildren().add(enemy);
        }
        return column;
    }

    private void makeTurnLabel(String msg) {
        currentTurn = new Label(msg);
        currentTurn.setFont(Font.font("suruma", 30));
        currentTurn.setUnderline(true);
        currentTurn.setTextAlignment(TextAlignment.CENTER);
        currentTurn.setAlignment(Pos.TOP_CENTER);
        currentTurn.setMinHeight(100);
        currentTurn.setMinWidth(800);
    }

    private ArrayList<HBox> initEnemyNodes() throws FileNotFoundException {
        EnemyNodeBuilder nodeBuilder = new EnemyNodeBuilder();
        nodeBuilder.setImagePath(RESOURCE_PATH + "enemy.png");
        nodeBuilder.setPosition(10, 10);
        nodeBuilder.setSize(75, 90);
        ArrayList<HBox> enemies = new ArrayList<>();
        for (var e: controller.getEnemies()) {
            nodeBuilder.setInfo(controller.getCharacterName(e),
                    controller.getCharacterHealth(e),
                    controller.getCharacterAttack(e),
                    controller.getCharacterDefense(e));
            enemies.add(nodeBuilder.build());
        }
        return enemies;
    }

    private ArrayList<HBox> initPlayerNodes() throws FileNotFoundException {
        Random random = new Random();
        PlayerNodeBuilder nodeBuilder = new PlayerNodeBuilder();
        nodeBuilder.setPosition(10, 10);
        nodeBuilder.setSize(75, 90);
        ArrayList<HBox> playerCharacters = new ArrayList<>();
        int i = 0;
        for (var e: controller.getCharacters()) {
            String imgName;
            switch (classes.get(e)){
                case "White Mage":
                    imgName = "wMage";
                    break;
                case "Black Mage":
                    imgName = "bMage";
                    break;
                default:
                    imgName = classes.get(e);
            }
            nodeBuilder.setImagePath(RESOURCE_PATH + imgName + ".gif");
            nodeBuilder.setInfo(controller.getCharacterName(e),
                    controller.getCharacterHealth(e),
                    controller.getCharacterAttack(e),
                    controller.getCharacterDefense(e),
                    classes.get(e));
            playerCharacters.add(nodeBuilder.build());
            i++;
        }
        return playerCharacters;
    }

    private VBox left() throws FileNotFoundException {
        Text enemyTitle = new Text("Enemies");
        enemyTitle.setFont(Font.font("Gubbi", FontWeight.BOLD, 30));
        enemyTitle.setStroke(Color.DARKRED);

        VBox enemies = makeColumn(initEnemyNodes(), enemyTitle, Pos.TOP_RIGHT, 20);
        enemies.setPadding(new Insets(100, 0, 0, 50));
        enemies.setStyle("-fx-border-width: 0 2 0 0; " +
                "-fx-border-color: red black green yellow;" +
                "-fx-padding: 50 50 0 10");
        return enemies;
    }

    private Group initialCenter() throws FileNotFoundException {
        main = new Group();
        center = new BorderPane();
        center.setPadding(new Insets(0, 0, 400, 0));
        main.getChildren().add(center);
        makeTurnLabel("Waiting for a character to start its turn");
        center.setTop(currentTurn);
        return main;
    }

    private VBox right() throws FileNotFoundException {
        Text playerTitle = new Text("Your \nCharacters");
        playerTitle.setFont(Font.font("Gubbi", FontWeight.BOLD, 30));
        playerTitle.setStroke(Color.DARKBLUE);

        VBox players = makeColumn(initPlayerNodes(), playerTitle, Pos.TOP_LEFT, 5);
        players.setPadding(new Insets(0, 50, 0, 0));
        players.setStyle("-fx-border-width: 0 0 0 2; " +
                "-fx-border-color: red black blue black;" +
                "-fx-padding: 0 40 0 50");
        return players;
    }

    public Scene build() throws FileNotFoundException {
        root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
                Color.color(159/255.0, 223/255.0, 234/255.0, 0.2),
                new CornerRadii(0),
                Insets.EMPTY)));
        root.setTop(top());
        root.setLeft(left());
        root.setRight(right());
        root.setCenter(initialCenter());
        controller.initTurns();
        setUpTimer();
        return new Scene(root, 1280, 720);
    }

    private void setUpTimer() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.setCenter(main);
            }
        };
        timer.start();
    }


    @Override
    public void playerTurn(ICharacter currentTurnChar) throws FileNotFoundException {
        System.out.println("Making View");
        main = new Group();
        BorderPane playerTurn = new BorderPane();
        playerTurn.setPadding(new Insets(0, 0, 100, 0));
        makeTurnLabel("It's " + controller.getCharacterName(currentTurnChar) + "'s Turn!");
        playerTurn.setTop(currentTurn);

        Button attack = new Button("Attack");
        attack.setOnAction(event -> {
            System.out.println("Pressed attack button");
            controller.toAttackPhase();
            try {
                chooseTarget(currentTurnChar);
            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }
        });
        Button equip = new Button("Equip a Weapon");
        equip.setOnAction(event -> {
            System.out.println("Pressed equip button");
            controller.toEquipPhase();
            try {
                chooseWeapon(currentTurnChar);
            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }
        });
        HBox buttons = new HBox();
        buttons.setSpacing(50);
        buttons.setPadding(new Insets(0, 0, 0, 300));
        buttons.getChildren().add(attack);
        buttons.getChildren().add(equip);

        VBox dialog = new VBox();
        IWeapon equippedWeapon = controller.getCharacterEquippedWeapon((IPlayerCharacter) currentTurnChar);
        String text;
        if (equippedWeapon != null) {
             text = controller.getCharacterName(currentTurnChar) +
                    " has the weapon " + controller.getWeaponName(equippedWeapon) + " equipped.";
        } else {
            text = controller.getCharacterName(currentTurnChar) + " has no weapon equipped.";
        }
        Label label = new Label(text);
        label.setFont(Font.font(15));
        dialog.setSpacing(50);
        dialog.getChildren().add(label);
        dialog.getChildren().add(buttons);
        dialog.setAlignment(Pos.CENTER);

        playerTurn.setCenter(dialog);
        center = playerTurn;
        main.getChildren().add(playerTurn);
        System.out.println("Player Turn is made");
    }

    private void chooseWeapon(ICharacter currentTurnChar) throws FileNotFoundException {
        main = new Group();
        BorderPane equipTurn = new BorderPane();
        equipTurn.setPadding(new Insets(0, 0, 100, 0));
        makeTurnLabel("It's " + controller.getCharacterName(currentTurnChar) + "'s Turn!");
        equipTurn.setTop(currentTurn);

        VBox content = new VBox();
        content.setSpacing(50);
        content.setAlignment(Pos.CENTER);

        FlowPane inventory = new FlowPane();
        Label instruction = new Label("Please choose a weapon");
        instruction.setFont(Font.font(15));
        instruction.setMinWidth(800);
        instruction.setAlignment(Pos.TOP_CENTER);
        inventory.getChildren().add(instruction);
        inventory.setAlignment(Pos.CENTER);
        inventory.setHgap(10);
        inventory.setVgap(10);
        inventory.setMinWidth(800);
        int i = 0;
        for(IWeapon w : controller.getInventory()) {
            inventory.getChildren().add(makeWeaponButton(currentTurnChar, w, i));
            i++;
        }
        Button goBack = new Button("Cancel");
        goBack.setOnAction(event -> {
            controller.goBack();
            try {
                playerTurn(currentTurnChar);
            } catch (FileNotFoundException e) {
            }
        });
        goBack.setMinWidth(300);
        inventory.setAlignment(Pos.CENTER);
        content.getChildren().add(inventory);
        content.getChildren().add(goBack);
        equipTurn.setCenter(content);
        center = equipTurn;
        main.getChildren().add(center);
    }

    private Button makeWeaponButton(ICharacter currentTurnChar, IWeapon weapon, int i) throws FileNotFoundException {
        Button b = new Button();
        WeaponNodeBuilder wb = new WeaponNodeBuilder();
        wb.setImagePath(RESOURCE_PATH + "weapon1.png");
        wb.setPosition(10, 10);
        wb.setSize(75, 90);
        wb.setInfo(controller.getWeaponName(weapon),
                controller.getWeaponWeight(weapon),
                controller.getWeaponDamage(weapon),
                weapons.get(weapon));
        b.setGraphic(wb.build());
        b.setOnAction(event -> {
            controller.tryToEquip(weapon);
            try {
                playerTurn(currentTurnChar);
            } catch (FileNotFoundException e) {
            }
        });
        return b;
    }

    private void chooseTarget(ICharacter currentTurnChar) throws FileNotFoundException {
        main = new Group();
        BorderPane targetTurn = new BorderPane();
        targetTurn.setPadding(new Insets(0, 0, 100, 0));
        makeTurnLabel("It's " + controller.getCharacterName(currentTurnChar) + "'s Turn!");
        targetTurn.setTop(currentTurn);

        VBox content = new VBox();
        content.setSpacing(50);
        content.setAlignment(Pos.CENTER);

        FlowPane enemies = new FlowPane();
        Label instruction = new Label("Please choose a target");
        instruction.setMinWidth(800);
        instruction.setFont(Font.font(15));
        instruction.setAlignment(Pos.TOP_CENTER);
        enemies.setHgap(10);
        enemies.setVgap(10);
        for(Enemy e : controller.getEnemies()) {
            enemies.getChildren().add(makeEnemyButton(currentTurnChar, e));
        }
        enemies.setAlignment(Pos.CENTER);
        Button back = new Button("Cancel");
        back.setOnAction(event -> {
            controller.goBack();
            try {
                playerTurn(currentTurnChar);
            } catch (FileNotFoundException e) {
            }
        });
        back.setMinWidth(300);
        back.setAlignment(Pos.BOTTOM_CENTER);
        content.getChildren().add(enemies);
        content.getChildren().add(back);
        targetTurn.setCenter(content);
        center = targetTurn;
        main.getChildren().add(center);
    }

    private Button makeEnemyButton(ICharacter character, Enemy e) throws FileNotFoundException {
        Button enemy = new Button();
        enemy.setOnAction(event -> {
            try {
                dialogTurn(character, controller.tryToAttack(e), e);
            } catch (FileNotFoundException fileNotFoundException) {
            }
        });
        EnemyNodeBuilder eb = new EnemyNodeBuilder();
        eb.setImagePath(RESOURCE_PATH + "enemy.png");
        eb.setPosition(10, 10);
        eb.setSize(75, 90);
        eb.setInfo(controller.getCharacterName(e),
                controller.getCharacterHealth(e),
                controller.getCharacterAttack(e),
                controller.getCharacterDefense(e));
        enemy.setGraphic(eb.build());
        return enemy;
    }

    @Override
    public void enemyTurn(ICharacter currentTurnChar) throws FileNotFoundException {
        main = new Group();
        BorderPane enemyTurn = new BorderPane();
        enemyTurn.setPadding(new Insets(0, 0, 100, 0));
        makeTurnLabel("It's " + controller.getCharacterName(currentTurnChar) + "'s Turn!");
        enemyTurn.setTop(currentTurn);

        // Create random index number
        long seed = new Random().nextLong();
        Random random = new Random(seed);

        // Find random target in Player roster
        int target = random.nextInt(controller.getCharacters().size());
        ICharacter character = controller.getCharacters().get(target);

        int damage = controller.tryToAttack(character);
        VBox dialog = new VBox();
        dialog.setSpacing(10);
        Label text = new Label(controller.getCharacterName(currentTurnChar) +
                " did " + damage + " damage to " +
                controller.getCharacterName(character));
        Button cont = new Button("Continue");
        cont.setOnAction(event -> {
            controller.toBeginTurnPhase();
            if (currentTurnChar == controller.getCurrentTurnCharacter()) {
                try {
                    initialCenter();
                } catch (FileNotFoundException e) {
                }
            }
        });
        cont.setAlignment(Pos.BOTTOM_RIGHT);
        text.setFont(Font.font(15));
        dialog.getChildren().add(text);
        dialog.getChildren().add(cont);
        dialog.setAlignment(Pos.CENTER);
        enemyTurn.setCenter(dialog);
        center = enemyTurn;
        main.getChildren().add(center);
        updatePlayer();
    }

    private void dialogTurn(ICharacter currentTurnChar, int damage, Enemy enemy) throws FileNotFoundException {
        main = new Group();
        BorderPane dialogTurn = new BorderPane();
        dialogTurn.setPadding(new Insets(0, 0, 100, 0));
        makeTurnLabel("It's " + controller.getCharacterName(currentTurnChar) + "'s Turn!");
        dialogTurn.setTop(currentTurn);

        VBox dialog = new VBox();
        dialog.setSpacing(10);
        Label text = new Label(controller.getCharacterName(currentTurnChar) +
                " did " + damage + " damage to " +
                controller.getCharacterName(enemy));
        Button cont = new Button("Continue");
        cont.setOnAction(event -> {
            controller.toBeginTurnPhase();
            if (currentTurnChar == controller.getCurrentTurnCharacter()) {
                try {
                    initialCenter();
                } catch (FileNotFoundException e) {
                }
            }
        });
        cont.setAlignment(Pos.BOTTOM_RIGHT);
        text.setFont(Font.font(15));
        dialog.getChildren().add(text);
        dialog.getChildren().add(cont);
        dialog.setAlignment(Pos.CENTER);
        dialogTurn.setCenter(dialog);
        center = dialogTurn;
        main.getChildren().add(center);
        updateEnemies();
    }

    private void updateEnemies() throws FileNotFoundException {
        root.setLeft(left());
    }

    private void updatePlayer() throws FileNotFoundException {
        root.setRight(right());
    }

    @Override
    public void winScene() {
        Scene scene = endScreenScene.buildWinScreen();
        primaryStage.setScene(scene);
    }

    @Override
    public void loseScene() {
        Scene scene = endScreenScene.buildLoseScreen();
        primaryStage.setScene(scene);
    }

}
