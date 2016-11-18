package com.example.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer{

    public static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
        cxt.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(cxt));
        cxt.setServletContext(servletContext);
        ServletRegistration.Dynamic servletRegistration =
                servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(cxt));
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }
}
