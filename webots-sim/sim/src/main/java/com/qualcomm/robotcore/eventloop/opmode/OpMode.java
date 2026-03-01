package com.qualcomm.robotcore.eventloop.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class OpMode {
    public HardwareMap hardwareMap = new HardwareMap();
    public Telemetry telemetry = null;

    public abstract void init();
    public abstract void loop();

    public void init_loop() {}
    public void start() {}
    public void stop() {}
}
