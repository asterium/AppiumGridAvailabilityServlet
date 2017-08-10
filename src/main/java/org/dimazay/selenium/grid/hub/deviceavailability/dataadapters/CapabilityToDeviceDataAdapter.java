package org.dimazay.selenium.grid.hub.deviceavailability.dataadapters;

import org.dimazay.selenium.grid.hub.deviceavailability.models.DeviceData;

import java.util.Map;

/**
 * Created by Asterium on 23.06.2017.
 */
public class CapabilityToDeviceDataAdapter {

    private static final String DEVICE_NAME_CAPABILITY = "deviceName";

    public DeviceData extractDeviceDataFromCapabilities(Map<String, Object> capabilities) {
        DeviceData deviceData = new DeviceData();
        if (capabilities.containsKey(DEVICE_NAME_CAPABILITY)) {
            String deviceName = capabilities.get(DEVICE_NAME_CAPABILITY).toString();
            deviceData.setCount(1);
            deviceData.addDeviceName(deviceName);
        }
        return deviceData;
    }
}
