package org.dimazay.selenium.grid.hub.controlpanel.web.application;

import org.dimazay.selenium.grid.hub.controlpanel.business.models.DeviceInfo;
import org.dimazay.selenium.grid.hub.controlpanel.business.service.DeviceInfoService;
import org.dimazay.selenium.grid.hub.deviceavailability.DeviceAvailability;
import org.dimazay.selenium.grid.hub.deviceavailability.models.BrowserData;
import org.dimazay.selenium.grid.hub.deviceavailability.util.MessageContentWriter;
import org.dimazay.selenium.grid.hub.deviceavailability.util.RegistryBrowserDataExtractor;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.web.servlet.RegistryBasedServlet;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public class ControlPanel extends RegistryBasedServlet {

    public static final Logger logger = Logger.getLogger(DeviceAvailability.class.getName());

    private DeviceInfoService dataExtractor;
    private AppiumControlPanelApplication appiumControlPanelApplication;

    public ControlPanel(){
        this(null);
    }

    public ControlPanel(Registry registry) {
        super(registry);
    }

    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        appiumControlPanelApplication = new AppiumControlPanelApplication(this.getServletContext());
        dataExtractor = new DeviceInfoService(getRegistry());
        logger.log(Level.INFO, "Request on control panel servlet");
        List<DeviceInfo> deviceList = dataExtractor.getListOfAvailableDevice();
        logger.warning(getServletContext().getServletContextName());
        ITemplateEngine templateEngine = appiumControlPanelApplication.getTemplateEngine();
        WebContext ctx = new WebContext(request, response, getServletContext());
        ctx.setVariable("deviceList", deviceList);

        templateEngine.process("controlpanel", ctx, response.getWriter());

    }
}
