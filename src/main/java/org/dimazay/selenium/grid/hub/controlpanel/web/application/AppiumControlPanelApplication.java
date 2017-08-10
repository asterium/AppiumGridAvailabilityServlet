package org.dimazay.selenium.grid.hub.controlpanel.web.application;

import org.dimazay.selenium.grid.hub.controlpanel.web.controller.ControlPanelController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Zaitsev on 8/9/2017.
 */
public class AppiumControlPanelApplication {

    private TemplateEngine templateEngine;
    private Map<String, ControlPanelController> controllersByURL;

    public AppiumControlPanelApplication(final ServletContext servletContext) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        // HTML is the default mode, but we will set it anyway for better understanding of code
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        // Cache is set to true by default. Set to false if you want templates to
        // be automatically updated when modified.
        templateResolver.setCacheable(false);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

//        this.controllersByURL = new HashMap<String, IGTVGController>();
//        this.controllersByURL.put("/", new HomeController());
//        this.controllersByURL.put("/product/list", new ProductListController());
//        this.controllersByURL.put("/product/comments", new ProductCommentsController());
//        this.controllersByURL.put("/order/list", new OrderListController());
//        this.controllersByURL.put("/order/details", new OrderDetailsController());
//        this.controllersByURL.put("/subscribe", new SubscribeController());
//        this.controllersByURL.put("/userprofile", new UserProfileController());

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
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }
}
