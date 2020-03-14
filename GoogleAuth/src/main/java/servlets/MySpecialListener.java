package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class MySpecialListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
        // On Application Startup, please…
    	System.out.println("Create Servlet");

        // Usually I'll make a singleton in here, set up my pool, etc.
    }

    @Override
	public void contextDestroyed(ServletContextEvent sce) {
        // On Application Shutdown, please…
    	System.out.println("Destroying Servlet");
    }

}