package com.qualcomm.robotcore.eventloop.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class LinearOpMode {
    public HardwareMap hardwareMap = new HardwareMap();
    public Telemetry telemetry = null;

    private volatile boolean isStarted = false;
    private volatile boolean stopRequested = false;

    public abstract void runOpMode();

    public void waitForStart() {
        while (!isStarted && !stopRequested) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public boolean opModeIsActive() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return isStarted && !stopRequested;
    }

    public boolean opModeInInit() {
        return !isStarted && !stopRequested;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isStopRequested() {
        return stopRequested;
    }

    public void setStarted(boolean started) {
        this.isStarted = started;
    }

    public void setStopRequested(boolean stop) {
        this.stopRequested = stop;
    }

    public void idle() {
        Thread.yield();
    }

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
