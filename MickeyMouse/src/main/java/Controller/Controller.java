package Controller;

import com.example.View.Game;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Controller {

    private static Game game = Game.getInstance();
    private static int basketPosition;
    private static int x = 800/2-25, y = 400/2-10; // Current basket coordinates
    private static TranslateTransition tt = new TranslateTransition(Duration.seconds(1));

    public static int getBasketPosition() {
        return basketPosition;
    }

    public static void moveToHouse0(){
        System.out.println("House 0");
        basketPosition = 0;

//        tt.setFromX(x);
//        tt.setFromX(y);
//        tt.setToX(150);
//        tt.setToY(210);
//        tt.setCycleCount(1);
//        tt.setNode(game.basket);
//        tt.play();
//
//        x = 150;
//        y = 210;
        game.basket.setLayoutX(150);
        game.basket.setLayoutY(210);
    }

    public static void moveToHouse1(){
        System.out.println("House 1");
        basketPosition = 1;

//        tt.setFromX(x);
//        tt.setFromX(y);
//        tt.setToX(150);
//        tt.setToY(360);
//        tt.setCycleCount(1);
//        tt.setNode(game.basket);
//        tt.play();
//
//        x = 150;
//        y = 360;
        game.basket.setLayoutX(150);
        game.basket.setLayoutY(360);
    }

    public static void moveToHouse2(){
        System.out.println("House 2");
        basketPosition = 2;

//        tt.setFromX(x);
//        tt.setFromX(y);
//        tt.setToX(600);
//        tt.setToY(210);
//        tt.setCycleCount(1);
//        tt.setNode(game.basket);
//        tt.play();
//
//        x = 600;
//        y = 210;
        game.basket.setLayoutX(650-50);
        game.basket.setLayoutY(210);
    }

    public static void moveToHouse3(){
        System.out.println("House 3");
        basketPosition = 3;

//        tt.setFromX(x);
//        tt.setFromX(y);
//        tt.setToX(150);
//        tt.setToY(360);
//        tt.setCycleCount(1);
//        tt.setNode(game.basket);
//        tt.play();
//
//        x = 600;
//        y = 360;
        game.basket.setLayoutX(650-50);
        game.basket.setLayoutY(360);
    }
}
