package com.example.View;

import Model.Score;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class GameOver extends Stage {

    public GameOver(int points){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final Label label = new Label("Enter your nickname:");
        GridPane.setConstraints(label, 0, 0);
        grid.getChildren().add(label);

        final TextField nickName = new TextField();
        nickName.setPrefColumnCount(10);
        GridPane.setConstraints(nickName, 0, 1);
        grid.getChildren().add(nickName);

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 1);
        grid.getChildren().add(submit);

        submit.setOnAction(e -> {
            String nick = nickName.getText();
            Score score = new Score(nick, points);
            ArrayList<Score> scores = new ArrayList<>();

            try {
                FileInputStream fis = new FileInputStream("data/highscores.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);

                scores = (ArrayList<Score>) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException ioe) {
                ioe.printStackTrace();
            }

            scores.add(score);
            try {
                FileOutputStream fos = new FileOutputStream("data/highscores.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(scores);
                oos.close();
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            Menu menu = new Menu();
            this.close();
            menu.show();
        });

        Scene scene = new Scene(grid, 400, 200);
        this.setScene(scene);
    }
}
