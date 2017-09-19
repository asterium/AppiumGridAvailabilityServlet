package org.dimazay.selenium.grid.hub.controlpanel.web.controller;

import org.dimazay.selenium.grid.hub.controlpanel.business.models.DeviceInfo;
import org.dimazay.selenium.grid.hub.controlpanel.business.service.DeviceInfoService;
import org.openqa.grid.internal.Registry;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Dmytro_Zaitsev on 8/10/2017.
 */
public class HomeController implements ControlPanelController {
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine, Registry registry) throws Exception {

        DeviceInfoService service = new DeviceInfoService(registry);
        List<DeviceInfo> deviceList = service.getListOfAvailableDevice();

        WebContext ctx = new WebContext(request, response, servletContext);
        ctx.setVariable("deviceList", deviceList);

        templateEngine.process("controlpanel", ctx, response.getWriter());
    }
}
