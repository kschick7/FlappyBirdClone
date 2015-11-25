package com.kyleschick.hoverboy;

/**
 * Created by Kyle on 11/25/2015.
 */
public class Clock {
    private long time;

    public Clock() {
        this.time = System.currentTimeMillis();
    }

    public void tick(int fps) {
        int timeGoal = 1000 / fps;
        while ((System.currentTimeMillis() - time) < timeGoal) {}
        time = System.currentTimeMillis();
    }
}
