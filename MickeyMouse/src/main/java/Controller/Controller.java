package Controller;
import com.example.View.Game;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Controller {

    private static Game game = Game.getInstance();
    private static int basketPosition;
    private static TranslateTransition tt = new TranslateTransition(Duration.seconds(1));

    public static int getBasketPosition() {
        return basketPosition;
    }

    public static void moveToHouse0(){
        basketPosition = 0;
        game.getFigure().setImage(game.getImages().get(0));
        game.getBasket().setLayoutX(150+10);
        game.getBasket().setLayoutY(150);
    }

    public static void moveToHouse1(){
        basketPosition = 1;
        game.getFigure().setImage(game.getImages().get(1));
        game.getBasket().setLayoutX(150+10);
        game.getBasket().setLayoutY(300);
    }

    public static void moveToHouse2(){
        basketPosition = 2;
        game.getFigure().setImage(game.getImages().get(2));
        game.getBasket().setLayoutX(650-50-10);
        game.getBasket().setLayoutY(150);
    }

    public static void moveToHouse3(){
        basketPosition = 3;
        game.getFigure().setImage(game.getImages().get(3));
        game.getBasket().setLayoutX(650-50-10);
        game.getBasket().setLayoutY(300);
    }
}
