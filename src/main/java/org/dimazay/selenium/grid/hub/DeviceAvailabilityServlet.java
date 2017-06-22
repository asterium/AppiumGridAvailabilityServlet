package org.dimazay.selenium.grid.hub;

import org.openqa.grid.internal.Registry;
import org.openqa.grid.web.servlet.RegistryBasedServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Dmytro_Zaitsev on 6/21/2017.
 */
public class DeviceAvailabilityServlet extends RegistryBasedServlet {

    private static final String AVAILABILITY_REQUEST_PATH = "/available";
    private final Logger log = Logger.getLogger(getClass().getName());
    private Registry registry;

    private RegistryBrowserDataExtractor dataExtractor;
    private MessageBuilder messageBuilder;

    public DeviceAvailabilityServlet() {
        this(null);


    }

    public DeviceAvailabilityServlet(Registry registry) {
        super(registry);
        this.registry = registry;

        System.out.println("Servlet added");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            dataExtractor = new RegistryBrowserDataExtractor(getRegistry());
            log.log(Level.WARNING, "Request on servlet");
            System.out.println("Request on servlet");
            messageBuilder = new MessageBuilder(response);
            Object browserData = dataExtractor.getListOfAvailableBrowsers();
            messageBuilder.buildResponseMessage(browserData);

    }
}





