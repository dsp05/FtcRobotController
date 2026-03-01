package com.qualcomm.robotcore.util;

public class ElapsedTime {
    private long nsStartTime;

    public ElapsedTime() {
        reset();
    }

    public void reset() {
        nsStartTime = System.nanoTime();
    }

    public double seconds() {
        return (System.nanoTime() - nsStartTime) / 1_000_000_000.0;
    }

    public double milliseconds() {
        return (System.nanoTime() - nsStartTime) / 1_000_000.0;
    }

    public double time() {
        return seconds();
    }

    @Override
    public String toString() {
        return String.format("%.3f seconds", seconds());
    }
}
