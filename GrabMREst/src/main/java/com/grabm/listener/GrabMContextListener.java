package com.grabm.listener;

import com.grabm.tracker.Server;
import com.grabm.util.GrabMConstant;
import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Sachith Dickwella
 */
@WebListener
public class GrabMContextListener implements ServletContextListener, GrabMConstant {    

    private static SessionFactory sessionFactoryDashboard;
    /**
     * Tracking server module server socket.
     */
    private static final int LISTENING_PORT = 30000;

    /**
     * @return the sessionFactoryDashboard
     */
    public static SessionFactory getSessionFactoryDashboard() {
        return sessionFactoryDashboard;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /**
         * Log4j property configuration from external file.
         */
        PropertyConfigurator.configure(getClass().getResource("/log/log4j.properties"));
        /**
         * Hibernate mapping configuration for dashboard database.
         */
        Configuration dashboard_config = new Configuration().configure("/hbm/hibernate_dashboard.cfg.xml");
        ServiceRegistry registry_dashboard = new StandardServiceRegistryBuilder()
                .applySettings(dashboard_config.getProperties()).build();
        sessionFactoryDashboard = dashboard_config.buildSessionFactory(registry_dashboard);
        /**
         * Start tracker server thread.
         */
        TRACKER_SERVICE.submit(SERVER_THREAD);
        /**
         * Set tracker server monitoring bit to 1.
         */
        ServerStatus.isRunning = true;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /**
         * Closing sessionFactoryDashboard reference at un-deployment.
         */
        if (sessionFactoryDashboard != null
                && !sessionFactoryDashboard.isClosed()) {
            sessionFactoryDashboard.close();
        }
        /**
         * Interrupt the server thread if is alive.
         */
        TRACKER_SERVICE.shutdown();
        /**
         * Set tracker server monitoring bit to 0.
         */
        ServerStatus.isRunning = false;
    }

    /**
     * Tracking server thread runnable.
     */
    private static final Runnable SERVER_THREAD = () -> {
        try {
            Server server = new Server(LISTENING_PORT);
            server.waitResponse();
        } catch (IOException e) {
            System.err.println(e);
        }
    };
}
