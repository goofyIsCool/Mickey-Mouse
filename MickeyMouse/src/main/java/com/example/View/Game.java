package com.example.View;

import Model.Egg;
import Controller.Controller;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Stage {

    private static Game instance;
    int GAME_WIDTH = 800;
    int GAME_HEIGHT = 400;

    AnimationTimer gameTimer;
    Timeline timeline;
    Scene gameScene;
    AnchorPane gamePane;

    int speed = 1;
    int health = 4;
    int points = 0;
    Label PointsLabel;
    Label HealthLabel;
    public Rectangle basket;
//    ArrayList<Egg> drop = new ArrayList<Egg>();

    public Game(){
        instance = this;
        initializeStage();
        createGameLoop();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        this.setScene(gameScene);

        PointsLabel = new Label("Points: 0");
        PointsLabel.setLayoutX(100);
        PointsLabel.setLayoutY(10);
        HealthLabel = new Label("Hearts: 4");
        HealthLabel.setLayoutX(10);
        HealthLabel.setLayoutY(10);
        basket = new Rectangle(50, 20);
        basket.setLayoutX(GAME_WIDTH/2-25);
        basket.setLayoutY(GAME_HEIGHT/2-10);
        basket.setFill(Color.GREEN);

        Line line0 = new Line();
        line0.setStartX(0);
        line0.setStartY(60);
        line0.setEndX(150);
        line0.setEndY(210);

        Line line1 = new Line();
        line1.setStartX(0);
        line1.setStartY(210);
        line1.setEndX(150);
        line1.setEndY(360);

        Line line2 = new Line();
        line2.setStartX(800);
        line2.setStartY(60);
        line2.setEndX(650);
        line2.setEndY(210);

        Line line3 = new Line();
        line3.setStartX(800);
        line3.setStartY(210);
        line3.setEndX(650);
        line3.setEndY(360);

        gamePane.getChildren().addAll(PointsLabel, HealthLabel, basket, line0, line1, line2, line3);

        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination( KeyCode.Q,
                    KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);

            public void handle(KeyEvent keyEvent) {
                if (keyComb.match(keyEvent)) {
                    System.out.println("Stop the game.");
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
    }

    private void createGameLoop() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            Egg egg = new Egg((int)(Math.random()*4));
//            drop.add(egg);
//            gamePane.getChildren().add(drop.get(drop.size() -1));
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

                if (egg.getHouse() == Controller.getBasketPosition())
                    PointsLabel.setText("Points: " + ++points);
                else
                    HealthLabel.setText("Hearts: " + --health);

//                drop.remove(egg);
            });

        }));

//        gameTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                gameUpdate();
//            }
//        };

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
//        gameTimer.start();
    }

//    public void catchOrDropEgg(Egg egg, int i){
//        if ((egg.getHouse()%2 == 0 && egg.getLayoutY() >= 200) || (egg.getHouse()%2 == 1 && egg.getLayoutY() >= 350)) {
//            if (egg.getHouse() == Controller.getBasketPosition()){
//                points += 10;
//                PointsLabel.setText("Points: " +  points);
//            }
//            else {
//                HealthLabel.setText("Hearts: " + --health);
//                if (health == 0) {
//                    timeline.stop();
//                    gameTimer.stop();
//
//                    GameOver gameOver = new GameOver(points);
//                    this.close();
//                    gameOver.show();
//                }
//            }
//
//            gamePane.getChildren().remove(egg);
//            drop.remove(i);
//        }
//    }
//
//    public void gameUpdate(){
//        for(int i = 0; i < drop.size(); i++) {
//            drop.get(i).setLayoutY(drop.get(i).getLayoutY() + speed);
//            if (drop.get(i).getHouse() < 2)
//                drop.get(i).setLayoutX(drop.get(i).getLayoutX() + speed);
//            else
//                drop.get(i).setLayoutX(drop.get(i).getLayoutX() - speed);
//
//            catchOrDropEgg(drop.get(i), i);
//        }
//    }

    public static Game getInstance(){
        return instance;
    }
}


