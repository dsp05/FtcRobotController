package org.firstinspires.ftc.simulator;

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SimpleAutonomous;

public class FtcBridge {

    public static void main(String[] args) {
        Robot robot = new Robot();
        int timeStep = (int) robot.getBasicTimeStep();

        // Get Webots motor devices
        Motor leftMotor = robot.getMotor("left_drive");
        Motor rightMotor = robot.getMotor("right_drive");
        leftMotor.setPosition(Double.POSITIVE_INFINITY);   // velocity mode
        rightMotor.setPosition(Double.POSITIVE_INFINITY);
        leftMotor.setVelocity(0.0);
        rightMotor.setVelocity(0.0);

        // Create mock FTC hardware
        SimDcMotor simLeft = new SimDcMotor("left_drive");
        SimDcMotor simRight = new SimDcMotor("right_drive");
        HardwareMap hwMap = new HardwareMap();
        hwMap.register("left_drive", simLeft);
        hwMap.register("right_drive", simRight);

        // Instantiate OpMode
        LinearOpMode opMode = new SimpleAutonomous();
        opMode.hardwareMap = hwMap;
        opMode.telemetry = new SimTelemetry();

        // Run OpMode on separate thread
        Thread opModeThread = new Thread(() -> {
            try {
                opMode.runOpMode();
            } catch (Exception e) {
                System.err.println("OpMode exception: " + e.getMessage());
                e.printStackTrace();
            }
        });
        opModeThread.setDaemon(true);
        opModeThread.start();

        // Let one timestep pass, then signal start
        robot.step(timeStep);
        System.out.println("[FtcBridge] Starting OpMode...");
        opMode.setStarted(true);

        // Main simulation loop
        double MAX_VELOCITY = 10.0; // rad/s
        while (robot.step(timeStep) != -1 && opModeThread.isAlive()) {
            leftMotor.setVelocity(simLeft.getEffectivePower() * MAX_VELOCITY);
            rightMotor.setVelocity(simRight.getEffectivePower() * MAX_VELOCITY);
        }

        // Cleanup
        opMode.setStopRequested(true);
        System.out.println("[FtcBridge] Simulation ended.");
    }
}
