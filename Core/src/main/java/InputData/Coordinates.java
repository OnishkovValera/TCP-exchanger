package InputData;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private float x;
    private double y;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Coordinates(float x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}
