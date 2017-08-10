package org.dimazay.selenium.grid.hub.deviceavailability.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class DeviceData implements Cloneable{

    private int count;
    private Set<String> deviceNames = new HashSet<String>();

    public DeviceData(){}

    public DeviceData(String deviceName) {
        this.deviceNames.add(deviceName);
        count = 1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<String> getDeviceNames() {
        return deviceNames;
    }

    public void setDeviceNames(Set<String> deviceNames) {
        this.deviceNames = deviceNames;
    }

    public void addDeviceName(String device){
        deviceNames.add(device);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceData that = (DeviceData) o;

        if (count != that.count) return false;
        return deviceNames.equals(that.deviceNames);

    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + deviceNames.hashCode();
        return result;
    }

}
