package org.dimazay.selenium.grid.hub.deviceavailability.models;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class BrowserData  implements Cloneable{

    private String browserName;
    private String platform;
    private DeviceData busy = new DeviceData();
    private DeviceData free = new DeviceData();

    public int getTotalDeviceCount(){
        return busy.getCount() + free.getCount();
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public DeviceData getBusy() {
        return busy;
    }

    public void setBusy(DeviceData busy) {
        this.busy = busy;
    }

    public DeviceData getFree() {
        return free;
    }

    public void setFree(DeviceData free) {
        this.free = free;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserData that = (BrowserData) o;

        if (browserName != null ? !browserName.equals(that.browserName) : that.browserName != null) return false;
        if (platform != null ? !platform.equals(that.platform) : that.platform != null) return false;
        if (busy != null ? !busy.equals(that.busy) : that.busy != null) return false;
        return free != null ? free.equals(that.free) : that.free == null;
    }

    @Override
    public int hashCode() {
        int result = browserName != null ? browserName.hashCode() : 0;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (busy != null ? busy.hashCode() : 0);
        result = 31 * result + (free != null ? free.hashCode() : 0);
        return result;
    }

}
