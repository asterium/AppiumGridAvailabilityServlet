package org.dimazay.selenium.grid.hub.controlpanel.web.controller;

import org.openqa.grid.internal.Registry;
import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public interface ControlPanelController {

    public void process(
            HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, ITemplateEngine templateEngine, Registry registry)
            throws Exception;
}
