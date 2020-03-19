package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Model.SessionData;

@WebListener
public class MySessionListener implements HttpSessionListener {

	private static int totalActiveSessions;

    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	ServletContext ctx  = sessionEvent.getSession().getServletContext();
		int timeoutValue = Integer.parseInt(ctx.getInitParameter("SESSIONTIMEOUT"));
		sessionEvent.getSession().setMaxInactiveInterval(timeoutValue);
    	totalActiveSessions++;
    	sessionEvent.getSession().setAttribute("sessionDataObject", new SessionData());
    	System.out.println("Session Created:: ID="+sessionEvent.getSession().getId());
    	System.out.println("Active Sessions:" + totalActiveSessions );
    	System.out.println("Sessions timeout:" + timeoutValue );
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	totalActiveSessions--;
    	System.out.println("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }

    public static int getTotalActiveSession(){
    	return totalActiveSessions;
    }
}