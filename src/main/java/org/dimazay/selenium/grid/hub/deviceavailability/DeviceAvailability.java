package org.dimazay.selenium.grid.hub.deviceavailability;

import org.dimazay.selenium.grid.hub.deviceavailability.models.BrowserData;
import org.dimazay.selenium.grid.hub.deviceavailability.util.MessageContentWriter;
import org.dimazay.selenium.grid.hub.deviceavailability.util.RegistryBrowserDataExtractor;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.web.servlet.RegistryBasedServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class DeviceAvailability extends RegistryBasedServlet {

    public static final Logger logger = Logger.getLogger(DeviceAvailability.class.getName());

    private RegistryBrowserDataExtractor dataExtractor;
    private MessageContentWriter messageContentWriter;

    public DeviceAvailability() {
        this(null);
    }

    public DeviceAvailability(Registry registry) {
        super(registry);
    }

    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            dataExtractor = new RegistryBrowserDataExtractor(getRegistry());
        logger.log(Level.INFO, "Request on availablity servlet");
        messageContentWriter = new MessageContentWriter(response);
        List<BrowserData> browserData = dataExtractor.getListOfAvailableBrowsers();
        messageContentWriter.buildResponseMessage(browserData);

    }
}





