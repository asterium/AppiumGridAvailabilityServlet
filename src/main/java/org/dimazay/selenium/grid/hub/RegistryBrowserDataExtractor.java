package org.dimazay.selenium.grid.hub;

import org.dimazay.selenium.grid.hub.models.BrowserData;
import org.dimazay.selenium.grid.hub.models.DeviceData;
import org.openqa.grid.internal.ProxySet;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.internal.RemoteProxy;
import org.openqa.grid.internal.TestSlot;

import java.util.*;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class RegistryBrowserDataExtractor {

    private static final String DEVICE_NAME_CAPABILITY = "deviceName";

    private Registry registry;
    private CapabilityToBrowserDataAdapter adapter;
    private String deviceNameCapability;


    public RegistryBrowserDataExtractor(Registry registry) {
        this.registry = registry;
        adapter = new CapabilityToBrowserDataAdapter();
    }

    public List<BrowserData> getListOfAvailableBrowsers(){
        List<BrowserData> mappedBrowserData = getUngroupedListOfBrowsers();
        List<BrowserData> result = reduceBrowsersList(mappedBrowserData);
        return result;
    }

    private List<BrowserData> reduceBrowsersList(List<BrowserData> mappedBrowserData) {
        Map<String, BrowserData> buckets = new HashMap<String, BrowserData>();
        for(BrowserData currentData : mappedBrowserData){
            String currentBrowserName = currentData.getBrowserName();
            if(buckets.containsKey(currentBrowserName)){
                BrowserData existingData = buckets.get(currentBrowserName);
                BrowserData mergedData = mergeResults(currentData, existingData);
                buckets.put(currentBrowserName, mergedData);
            }
            else {
               buckets.put(currentData.getBrowserName(), currentData.clone());
            }
        }
        return new ArrayList<BrowserData>(buckets.values());
    }

    private List<BrowserData> getUngroupedListOfBrowsers(){
        List<BrowserData> mappedBrowserData = new ArrayList<BrowserData>();

        ProxySet proxies = registry.getAllProxies();
        for (RemoteProxy proxy : proxies){
            for(TestSlot testSlot : proxy.getTestSlots()){
                Map<String, Object> testSlotCapabilities = testSlot.getCapabilities();
                BrowserData browserData = adapter.extractBrowserDataFromCapabilities(testSlotCapabilities);

                String deviceName = testSlotCapabilities.get(DEVICE_NAME_CAPABILITY).toString();
                DeviceData deviceData  = new DeviceData(deviceName);

                if(isTestSlotFree(testSlot)){
                    browserData.setFree(deviceData);
                }else
                {
                    browserData.setBusy(deviceData);
                }

                mappedBrowserData.add(browserData);
            }
        }

        return mappedBrowserData;
    }

    private BrowserData mergeResults(BrowserData first, BrowserData second){
        if(!first.getBrowserName().equals(second.getBrowserName())){
            throw new IllegalArgumentException("Could not merge browser data with different browser names");
        }

        BrowserData result = new BrowserData();

        DeviceData mergedFreeData = mergeDeviceData(first.getFree(), second.getFree());
        DeviceData mergedBusyData = mergeDeviceData(first.getBusy(), second.getBusy());

        result.setFree(mergedFreeData);
        result.setBusy(mergedBusyData);

        return result;
    }

    private DeviceData mergeDeviceData(DeviceData first, DeviceData second){
        DeviceData result = new DeviceData();

        int count = first.getCount() + second.getCount();
        Set<String> resultingDeviceNames = new HashSet<String>(first.getDeviceNames());
        resultingDeviceNames.addAll(second.getDeviceNames());

        result.setCount(count);
        result.setDeviceNames(resultingDeviceNames);

        return result;
    }

    private boolean isTestSlotFree(TestSlot slot){
        return slot.getSession() == null;
    }


}
