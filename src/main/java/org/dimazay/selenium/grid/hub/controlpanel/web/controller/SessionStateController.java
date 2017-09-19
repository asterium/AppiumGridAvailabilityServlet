package org.dimazay.selenium.grid.hub.controlpanel.web.controller;

import org.openqa.grid.internal.Registry;
import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dmytro_Zaitsev on 8/11/2017.
 */
public class SessionStateController implements ControlPanelController {
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine, Registry registry) throws Exception {
        String sessionid = request.getParameter("sessionId");
        org.dimazay.selenium.grid.hub.controlpanel.business.service.SessionStateService detailsService = new org.dimazay.selenium.grid.hub.controlpanel.business.service.SessionStateService(registry);
        detailsService.killTestSession(sessionid);

    }
}