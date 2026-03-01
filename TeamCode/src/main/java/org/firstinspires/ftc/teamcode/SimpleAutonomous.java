package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Simple Autonomous", group = "Examples")
public class SimpleAutonomous extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        // Initialize hardware
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // Step 1: Drive forward for 2 seconds
        telemetry.addData("Step", "1 - Driving forward");
        telemetry.update();
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.0) {
            telemetry.addData("Step", "1 - Driving forward (%.1f s)", runtime.seconds());
            telemetry.update();
        }

        // Step 2: Turn right for 1 second
        telemetry.addData("Step", "2 - Turning right");
        telemetry.update();
        leftDrive.setPower(0.5);
        rightDrive.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.0) {
            telemetry.addData("Step", "2 - Turning right (%.1f s)", runtime.seconds());
            telemetry.update();
        }

        // Step 3: Drive forward for 1 second
        telemetry.addData("Step", "3 - Driving forward");
        telemetry.update();
        leftDrive.setPower(1);
        rightDrive.setPower(1);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.0) {
            telemetry.addData("Step", "3 - Driving forward (%.1f s)", runtime.seconds());
            telemetry.update();
        }

        // Step 4: Stop
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        telemetry.addData("Step", "Complete");
        telemetry.update();

        // Keep the OpMode alive so telemetry remains visible
        while (opModeIsActive()) {
            telemetry.addData("Status", "Done. Run time: %.1f s", runtime.seconds());
            telemetry.update();
        }
    }
}
