package Listeners;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    	ServletRequest servletRequest = servletRequestEvent.getServletRequest();
    	System.out.println("ServletRequest destroyed. Remote IP="+servletRequest.getRemoteAddr());
    	/*
    	HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
    	HttpSession session = request.getSession();
		System.out.println("Destroyed Session creation:" + session.getCreationTime());
		System.out.println("Destroyed creation:" + session.getId());
		*/
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    	ServletRequest servletRequest = servletRequestEvent.getServletRequest();
    	System.out.println("ServletRequest initialized. Remote IP="+servletRequest.getRemoteAddr());
    	/*
    	HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
    	HttpSession session = request.getSession();
		System.out.println("Initialized Session creation:" + session.getCreationTime());
		System.out.println("Initialized Session creation:" + session.getId());
		*/
    }
	
}