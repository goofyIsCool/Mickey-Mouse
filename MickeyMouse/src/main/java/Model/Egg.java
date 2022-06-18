package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Egg extends Ellipse {

    private int Ax, Ay;
    private int Bx, By;
    private int house;

    public Egg(int house) {
        switch (house) {
            case 0 -> {
                Ax = 0;
                Ay = 25;
                Bx = 150;
                By = 210-35;
            }
            case 1 -> {
                Ax = 0;
                Ay = 100;
                Bx = 150;
                By = 250;
            }
            case 2 -> {
                Ax = 400;
                Ay = 25;
                Bx = 300;
                By = 210-35;
            }
            case 3 -> {
                Ax = 400;
                Ay = 100;
                Bx = 300;
                By = 250;
            }
        }

        this.house = house;
        this.setLayoutX(Ax);
        this.setLayoutY(Ay);
        this.setRadiusX(5);
        this.setRadiusY(7);
        this.setFill(Color.YELLOW);
    }

    public int getHouse(){
        return house;
    }

    public int getAx() {
        return Ax;
    }

    public int getAy() {
        return Ay;
    }

    public int getBx() {
        return Bx;
    }

    public int getBy() {
        return By;
    }
}
