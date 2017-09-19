package org.dimazay.selenium.grid.hub.controlpanel.web.application;

import org.dimazay.selenium.grid.hub.controlpanel.web.controller.ControlPanelController;
import org.dimazay.selenium.grid.hub.controlpanel.web.controller.HomeController;
import org.dimazay.selenium.grid.hub.controlpanel.web.controller.SessionStateController;
import org.openqa.grid.internal.Registry;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public class AppiumControlPanelApplication {

    private static final String SERVLET_BASE_PATH = "/grid/admin/ControlPanel";

    private final TemplateEngine templateEngine;
    private final Map<String, ControlPanelController> controllersByURL;
    private final Registry gridRegistry;
    private final ServletContext context;

    public AppiumControlPanelApplication(final Registry registry, final ServletContext servletContext) {

        gridRegistry = registry;
        context = servletContext;

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        // HTML is the default mode, but we will set it anyway for better understanding of code
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

        this.controllersByURL = new HashMap<String, ControlPanelController>();
        this.controllersByURL.put("/", new HomeController());
        this.controllersByURL.put("/sessions/doDelete", new SessionStateController());

    }

    public ITemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }

    public ControlPanelController resolveControllerForRequest(final HttpServletRequest request) {
        final String path = getRequestPath(request);
        return this.controllersByURL.get(path);
    }

    private static String getRequestPath(final HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        String trimmedURI = requestURI.replace(SERVLET_BASE_PATH, "");
        String relativeURI = trimmedURI.startsWith("/")? trimmedURI: "/"+trimmedURI;
        String result;

        final int fragmentIndex = trimmedURI.indexOf('?');
        if (fragmentIndex != -1) {
            result = trimmedURI.substring(0, fragmentIndex);
        }else{
          result = relativeURI;
        }
        return result;

    }


}
