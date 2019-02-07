package org.dimazay.selenium.grid.hub.controlpanel.business.models;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public class DeviceInfo {
    private String name;
    private String browserName;
    private boolean isBusy;
    private String sessionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }
}
