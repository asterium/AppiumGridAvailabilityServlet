package org.dimazay.selenium.grid.hub.models;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class BrowserData  implements Cloneable{

    private String browserName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserData that = (BrowserData) o;

        if (!browserName.equals(that.browserName)) return false;
        if (!busy.equals(that.busy)) return false;
        return free.equals(that.free);

    }

    @Override
    public int hashCode() {
        int result = browserName.hashCode();
        result = 31 * result + busy.hashCode();
        result = 31 * result + free.hashCode();
        return result;
    }

    @Override
    public  BrowserData clone() {
        BrowserData result = new BrowserData();
        DeviceData newFree = getFree().clone();
        DeviceData newBusy = getBusy().clone();

        result.setBrowserName(this.getBrowserName());
        result.setBusy(newBusy);
        result.setFree(newFree);
        return result;
    }
}
