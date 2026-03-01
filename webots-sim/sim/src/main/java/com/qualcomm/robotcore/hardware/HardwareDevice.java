package com.qualcomm.robotcore.hardware;

public interface HardwareDevice {
    enum Manufacturer {
        Unknown, Other
    }

    Manufacturer getManufacturer();
    String getDeviceName();
    String getConnectionInfo();
    int getVersion();
    void resetDeviceConfigurationForOpMode();
    void close();
}
