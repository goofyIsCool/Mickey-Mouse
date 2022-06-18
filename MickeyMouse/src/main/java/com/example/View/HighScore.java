package com.example.View;

import Controller.Controller;
import Model.Score;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class HighScore extends Stage {
    public HighScore () {
        ScrollPane scrollPane = new ScrollPane();

        ArrayList<Score> scores = new ArrayList<>();
        ListView<String> scoreList = new ListView();
        try {
            FileInputStream fis = new FileInputStream("data/highScores.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            scores = (ArrayList<Score>) ois.readObject();
            scores.sort((c1, c2) -> (int) (c2.getHighscore() - c1.getHighscore()));

            ois.close();
            fis.close();
        }
        catch (IOException | ClassNotFoundException ioe) {
                    ioe.printStackTrace();
        }

        for (Score sc : scores) {
            System.out.println(sc.string());
        }

        for (Score score: scores) {
            scoreList.getItems().add(score.string());
        }

        Button back = new Button("Back");
        back.setOnAction(e->{
            Menu menu = new Menu();
            this.close();
            menu.show();
        });

        scrollPane.setContent(scoreList);

        Scene scene = new Scene(scrollPane, 400, 400);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.ESCAPE);

            public void handle(KeyEvent keyEvent) {
                if (keyComb.match(keyEvent)) {
                    Menu menu = new Menu();
                    HighScore.this.close();
                    menu.show();
                }
            }
        });

        this.setTitle("High Score");
        this.setScene(scene);
    }
}
