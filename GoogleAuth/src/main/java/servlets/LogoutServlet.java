package servlets;

import java.io.IOException;  
  
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "logoutservlet",
        urlPatterns = "/LogoutServlet"
)
public class LogoutServlet extends HttpServlet {  
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            HttpSession session=request.getSession();
            System.out.println("Session creation:" + session.getCreationTime());
			System.out.println("Session creation:" + session.getId());
			System.out.println("Session valid:" + request.isRequestedSessionIdValid());
            session.invalidate();  
			System.out.println("Session valid:" + request.isRequestedSessionIdValid());
    		response.setContentType("text/plain;charset=UTF-8");
    		ServletOutputStream sout = response.getOutputStream();
    		sout.print("LoggedOut");
    }  
}  