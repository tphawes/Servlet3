package Listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {

	private static int totalActiveSessions;

    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	totalActiveSessions++;
    	System.out.println("Session Created:: ID="+sessionEvent.getSession().getId());
    	
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	totalActiveSessions--;
    	System.out.println("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }

    public static int getTotalActiveSession(){
    	return totalActiveSessions;
    }
}