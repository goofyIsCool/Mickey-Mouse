package com.example.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu extends Stage {

    public Menu() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);

        Label mickeyMouse = new Label("Mickey mouse");
        mickeyMouse.setFont(new Font("Arial", 24));

        Button newGame = new Button("   New Game   ");
        GridPane.setRowIndex(newGame, 1);
        GridPane.setColumnIndex(newGame, 0);
        newGame.setOnAction(e -> {
            Game g = new Game();
            this.close();
            g.show();
        });

        Button highScore = new Button("   High Scores   ");
        GridPane.setRowIndex(highScore, 2);
        GridPane.setColumnIndex(highScore, 0);
        highScore.setOnAction(e -> {
            HighScore hs = new HighScore();
            this.close();
            hs.show();
        });

        Button exit = new Button("   Exit   ");
        GridPane.setRowIndex(exit, 3);
        GridPane.setColumnIndex(exit, 0);
        exit.setOnAction(e -> {
            System.exit(0);
        });

        root.getChildren().addAll(mickeyMouse, newGame, highScore, exit);
        Scene scene = new Scene(root, 400, 400);
        this.setTitle("Mickey Mouse");
        this.setScene(scene);
        this.show();
    }
}
