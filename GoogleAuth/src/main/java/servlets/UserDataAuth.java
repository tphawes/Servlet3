package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.riversun.oauth2.google.OAuthSession;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import auth.IdTokenVerifierAndParser;

@WebServlet(
        name = "userdataauth",
        urlPatterns = "/UserDataAuth"
)
public class UserDataAuth extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("UserDataAuth start");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("User name: " + username);
        System.out.println("User password: " + password);
		HttpSession session = request.getSession(true);
		System.out.println("Session creation:" + session.getCreationTime());
		System.out.println("Session creation:" + session.getId());
		String returnVal=username + ":" + session.getId();
        session.setAttribute("userName", username);
		response.setContentType("text/plain;charset=UTF-8");
        ServletOutputStream sout = response.getOutputStream();
        sout.print(returnVal);
        System.out.println("UserDataAuth complete:" + returnVal);

    }
}