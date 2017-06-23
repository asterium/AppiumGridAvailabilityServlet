package org.dimazay.selenium.grid.hub;

import org.dimazay.selenium.grid.hub.models.BrowserData;
import org.dimazay.selenium.grid.hub.util.MessageBuilder;
import org.dimazay.selenium.grid.hub.util.RegistryBrowserDataExtractor;
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

    private static final String AVAILABILITY_REQUEST_PATH = "/available";
    private final Logger log = Logger.getLogger(getClass().getName());

    private RegistryBrowserDataExtractor dataExtractor;
    private MessageBuilder messageBuilder;

    public DeviceAvailability() {
        this(null);
    }

    public DeviceAvailability(Registry registry) {
        super(registry);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            dataExtractor = new RegistryBrowserDataExtractor(getRegistry());
        log.log(Level.INFO, "Request on availablity servlet");
        messageBuilder = new MessageBuilder(response, log);
        List<BrowserData> browserData = dataExtractor.getListOfAvailableBrowsers();
            messageBuilder.buildResponseMessage(browserData);

    }
}





