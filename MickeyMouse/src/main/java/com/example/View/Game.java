package com.example.View;
import Model.Egg;
import Controller.Controller;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game extends Stage {

    private static Game instance;
    private Timeline timeline;
    Timeline eggTimeline;
    private final AnchorPane gamePane;

    private int health = 4;
    private int points = 0;
    private final Label PointsLabel;
    private final Rectangle basket;
    private ImageView figure;
    private ArrayList<Image> images;
    private ArrayList<ImageView> hearts;
    private ArrayList<ImageView> brokenEggs;
    int counter = 0;

    public Game() {
        int GAME_WIDTH = 800;
        int GAME_HEIGHT = 400;

        instance = this;
        gamePane = new AnchorPane();
        Scene gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        hearts = new ArrayList<>();

        try{
            ImageView background = new ImageView(new Image(new FileInputStream("Graphics\\background.png")));
            background.setX(0);
            background.setY(0);
            background.setFitWidth(800);
            background.setFitHeight(400);
            background.setPreserveRatio(true);
            gamePane.getChildren().add(background);

            for (int i = 0; i < 4; i++) {
                ImageView tmp = new ImageView(new Image(new FileInputStream("Graphics\\heart.png")));
                tmp.setFitWidth(40);
                tmp.setFitHeight(40);
                tmp.setX(40+i*40);
                tmp.setY(10);
                hearts.add(tmp);
                gamePane.getChildren().add(tmp);
            }

            brokenEggs = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                ImageView brokenEgg = new ImageView(new Image(new FileInputStream("Graphics\\egg.png")));
                brokenEgg.setFitWidth(40);
                brokenEgg.setFitHeight(40);
                brokenEgg.setY(300);
                brokenEggs.add(brokenEgg);
            };

            images = new ArrayList<>();
            for (int i = 0; i < 4; i++){
                Image tmp = new Image(new FileInputStream("Graphics\\boba" + i + ".png"));
                images.add(tmp);
            }
            figure = new ImageView(images.get(0));
            figure.setX(GAME_WIDTH /2-70);
            figure.setY(GAME_HEIGHT /2-70);
            figure.setFitWidth(200);
            figure.setFitHeight(400);
            figure.setPreserveRatio(true);
            gamePane.getChildren().add(figure);
        }
        catch (FileNotFoundException ignored) {}

        this.setScene(gameScene);
        PointsLabel = new Label("Points: 0");
        PointsLabel.setFont(new Font("Arial", 16));
        PointsLabel.setLayoutX(800-200);
        PointsLabel.setLayoutY(10);
        basket = new Rectangle(50, 20);
        basket.setLayoutX(150 + 10);
        basket.setLayoutY(150);
        basket.setFill(Color.RED);

        Line line0 = new Line();
        line0.setStartX(0);
        line0.setStartY(60);
        line0.setEndX(150);
        line0.setEndY(150);
        line0.setStrokeWidth(10);

        Line line1 = new Line();
        line1.setStartX(0);
        line1.setStartY(210);
        line1.setEndX(150);
        line1.setEndY(300);
        line1.setStrokeWidth(10);

        Line line2 = new Line();
        line2.setStartX(800);
        line2.setStartY(60);
        line2.setEndX(650);
        line2.setEndY(150);
        line2.setStrokeWidth(10);

        Line line3 = new Line();
        line3.setStartX(800);
        line3.setStartY(210);
        line3.setEndX(650);
        line3.setEndY(300);
        line3.setStrokeWidth(10);

        gamePane.getChildren().addAll(PointsLabel, basket, line0, line1, line2, line3);
        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.Q,
                    KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);

            public void handle(KeyEvent keyEvent) {
                if (keyComb.match(keyEvent)) {
                    Menu menu = new Menu();
                    timeline.stop();
                    Game.getInstance().close();
                    menu.show();
                }
                switch (keyEvent.getCode()) {
                    case Q -> Controller.moveToHouse0();
                    case A -> Controller.moveToHouse1();
                    case P -> Controller.moveToHouse2();
                    case L -> Controller.moveToHouse3();
                }
            }
        });

        createGameLoop();
    }

    private void createGameLoop() {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), event -> {
            Egg egg = new Egg((int)(Math.random()*4));
            gamePane.getChildren().add(egg);

            Path path = new Path();
            PathTransition pathTransition = new PathTransition(Duration.seconds(6), egg);
            path.getElements().add(new MoveTo(egg.getAx(), egg.getAy()));
            path.getElements().add(new LineTo(egg.getBx(), egg.getBy()));
            pathTransition.setPath(path);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);

            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), egg);
            rotateTransition.setByAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setCycleCount(2);

            ParallelTransition pt = new ParallelTransition(egg, pathTransition, rotateTransition);
            pt.play();

            pt.setOnFinished(e -> {
                gamePane.getChildren().remove(egg);
                if (egg.getHouse() != Controller.getBasketPosition())
                {
                    gamePane.getChildren().remove(hearts.get(--health));
                    if (health == 0) {
                        GameOver gameOver = new GameOver(points);
                        timeline.stop();
                        eggTimeline.stop();
                        Game.getInstance().close();
                        gameOver.show();
                    }
                    try {
                        if (egg.getHouse() < 2) {
                            brokenEggs.get(counter).setX(150);
                        }
                        else
                            brokenEggs.get(counter).setX(650-40);

                        gamePane.getChildren().add(brokenEggs.get(counter));
                        eggTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {
                            gamePane.getChildren().remove(brokenEggs.get(counter++));
                        }));
                        eggTimeline.play();
                    }
                    catch (IndexOutOfBoundsException|IllegalArgumentException ignored) {}
                }
                else {
                    PointsLabel.setText("Points: " + ++points);
                    if (points < 10)
                        timeline.setRate(2);
                    else if (points > 20 && points < 40)
                        timeline.setRate(3);
                    else
                        timeline.setRate(6);
                }
            });
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static Game getInstance(){
        return instance;
    }

    public ImageView getFigure() {
        return figure;
    }

    public Rectangle getBasket() {
        return basket;
    }

    public ArrayList<Image> getImages() {
        return images;
    }
}


