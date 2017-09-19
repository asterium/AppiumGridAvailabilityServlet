package org.dimazay.selenium.grid.hub.controlpanel.web.application;

import org.dimazay.selenium.grid.hub.controlpanel.business.service.DeviceInfoService;
import org.dimazay.selenium.grid.hub.controlpanel.web.controller.ControlPanelController;
import org.dimazay.selenium.grid.hub.controlpanel.web.controller.NotFoundController;
import org.dimazay.selenium.grid.hub.deviceavailability.DeviceAvailability;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.web.servlet.RegistryBasedServlet;
import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public class ControlPanel extends RegistryBasedServlet {

    public static final Logger logger = Logger.getLogger(DeviceAvailability.class.getName());

    private DeviceInfoService dataExtractor;
    private AppiumControlPanelApplication application;

    public ControlPanel(){
        this(null);
    }

    public ControlPanel(Registry registry) {
        super(registry);
    }

    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        application = new AppiumControlPanelApplication(getRegistry(), getServletContext());
        logger.log(Level.INFO, "Request on control panel servlet");
        process(request, response);
//        ControlPanelController controller = application.resolveControllerForRequest(request);
//        ITemplateEngine templateEngine = application.getTemplateEngine();
//        controller.process(request,response,getServletContext(),templateEngine,getRegistry());
//        List<DeviceInfo> deviceList = dataExtractor.getListOfAvailableDevice();
//        logger.warning(getServletContext().getServletContextName());
//        ITemplateEngine templateEngine = application.getTemplateEngine();
//        WebContext ctx = new WebContext(request, response, getServletContext());
//        ctx.setVariable("deviceList", deviceList);
//
//        templateEngine.process("controlpanel", ctx, response.getWriter());

    }


    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            ControlPanelController controller = this.application.resolveControllerForRequest(request);
            if (controller == null) {
                controller = new NotFoundController();
            }

            ITemplateEngine templateEngine = this.application.getTemplateEngine();
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            controller.process(
                    request, response, getServletContext(), templateEngine, getRegistry());

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // Just ignore this
            }
            throw new ServletException(e);
        }

    }
}
