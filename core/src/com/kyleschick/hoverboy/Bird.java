package com.kyleschick.hoverboy;

/**
 * Created by Kyle on 11/25/2015.
 */
public class Bird {
    private int x;
    private int y;
    private double verticleSpeed;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        this.verticleSpeed = 0;
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

    public void changeVerticleSpeed(double difference) {
        this.verticleSpeed += difference;
    }

    public void setVerticleSpeed(double verticleSpeed) {
        this.verticleSpeed = verticleSpeed;
    }

    public void move() {
        this.y += verticleSpeed;
    }
}
