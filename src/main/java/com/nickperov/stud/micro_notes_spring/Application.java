package com.nickperov.stud.micro_notes_spring;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";

    private static WebApplicationContext webApplicationContext = null;
    private static volatile boolean isRunning = false;
    private static volatile int port;
    
    private static volatile Server server;

    public static void main(String[] args) throws RuntimeException {
        logger.info("Starting notes application");

        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(Application.class);

        webApplicationContext = context;
        
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = DEFAULT_PORT;
        }

        final ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.setErrorHandler(null);
        contextHandler.addEventListener(new ContextLoaderListener(context));

        server = new Server();
        try (final ServerConnector connector = new ServerConnector(server)) {
            logger.info("Starting jetty server on port: " + port);
            connector.setPort(port);
            server.setConnectors(new Connector[]{connector});
            server.setHandler(contextHandler);
            server.start();
            logger.info("Jetty server started");
            isRunning = true;
            server.join();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start web server", e);
        }
    }

    public static WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public static boolean isReady() {
        return isRunning;
    }

    public static int getPort() {
        return port;
    }
    
    public static void stop() throws Exception {
        server.stop();
    }
}