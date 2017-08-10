package org.dimazay.selenium.grid.hub.deviceavailability.dataadapters;

import org.dimazay.selenium.grid.hub.deviceavailability.models.BrowserData;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Map;

/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class CapabilityToBrowserDataAdapter {

    public BrowserData extractBrowserDataFromCapabilities(Map<String, Object> capabilities){
        BrowserData result = new BrowserData();
        String browserName = capabilities.get(CapabilityType.BROWSER_NAME).toString();
        String platform = capabilities.get(CapabilityType.PLATFORM).toString();
        result.setBrowserName(browserName);
        result.setPlatform(platform);
        return result;
    }

}
