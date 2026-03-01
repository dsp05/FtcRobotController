package com.qualcomm.robotcore.hardware;

import java.util.HashMap;
import java.util.Map;

public class HardwareMap {
    private final Map<String, HardwareDevice> deviceMap = new HashMap<>();

    public void register(String name, HardwareDevice device) {
        deviceMap.put(name, device);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<? extends T> classOrInterface, String deviceName) {
        HardwareDevice device = deviceMap.get(deviceName);
        if (device == null) {
            throw new IllegalArgumentException("Unable to find device: " + deviceName);
        }
        return (T) device;
    }
}
