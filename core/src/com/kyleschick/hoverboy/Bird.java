package com.kyleschick.hoverboy;

/**
 * Created by Kyle on 11/25/2015.
 */
public class Bird {
    private int x;
    private int y;
    private double verticalSpeed;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        this.verticalSpeed = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void changeVerticalSpeed(double difference) {
        this.verticalSpeed += difference;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public boolean isFalling() {
        return this.verticalSpeed < 0;
    }

    public void move() {
        this.y += verticalSpeed;
        if (this.y < 0) {
            this.y = 0;
        }
        else if (this.y > 610) {
            this.y = 610;
            verticalSpeed = 0;
        }
    }
}
