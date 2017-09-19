package org.dimazay.selenium.grid.hub.controlpanel.business.service;

import org.dimazay.selenium.grid.hub.controlpanel.business.models.DeviceInfo;
import org.dimazay.selenium.grid.hub.deviceavailability.models.BrowserData;
import org.dimazay.selenium.grid.hub.deviceavailability.models.DeviceData;
import org.openqa.grid.internal.*;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public class DeviceInfoService {

    private static final String DEVICE_NAME_CAPABILITY = "deviceName";
    private static final String SESSION_BEING_CREATED_MSG = "Session is being created";
    private final Registry registry;

    public DeviceInfoService(Registry registry) {
        this.registry = registry;
    }

    public List<DeviceInfo> getListOfAvailableDevice(){
        List<DeviceInfo> listOfAllDevices = new ArrayList<DeviceInfo>();
        ProxySet proxies = registry.getAllProxies();
        for (RemoteProxy proxy : proxies){
            for(TestSlot testSlot : proxy.getTestSlots()){
                Map<String, Object> testSlotCapabilities = testSlot.getCapabilities();
                DeviceInfo deviceInfo = new DeviceInfo();
                String browserName =  testSlotCapabilities.get(CapabilityType.BROWSER_NAME).toString();
                String deviceName = testSlotCapabilities.containsKey(DEVICE_NAME_CAPABILITY) ? testSlotCapabilities.get(DEVICE_NAME_CAPABILITY).toString() : null;

                deviceInfo.setBrowserName(browserName);
                deviceInfo.setName(deviceName);

                deviceInfo.setBusy(!isTestSlotFree(testSlot));

                if(!isTestSlotFree(testSlot)){
                    TestSession testSession = testSlot.getSession();
                    deviceInfo.setSessionId(getSessionState(testSession));
                    deviceInfo.setSessionCreated(!getSessionState(testSession).equals(SESSION_BEING_CREATED_MSG));
                }

                listOfAllDevices.add(deviceInfo);
            }
        }

        return listOfAllDevices;
    }

    private String getSessionState( TestSession testSession) {
        String result = SESSION_BEING_CREATED_MSG;
                    if(testSession.getExternalKey() != null) {
                result = testSession.getExternalKey().getKey();
            }
        return result;
    }


    private boolean isTestSlotFree(TestSlot slot){
        return slot.getSession() == null;
    }
}
