package servlets;

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

    	try {
        // 1. Go fetch that DataSource
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource datasource = (DataSource)envContext.lookup("jdbc/database");

        // 2. Deregister Driver

            java.sql.Driver mySqlDriver = DriverManager.getDriver("jdbc:mysql://localhost:3306/");
            DriverManager.deregisterDriver(mySqlDriver);
            datasource = null;

        } catch (SQLException ex) {
            ex.printStackTrace();;
        } 
    	 catch (Exception ex) {
             ex.printStackTrace();;
         }
        // 3. For added safety, remove the reference to dataSource for GC to enjoy.
    }

}