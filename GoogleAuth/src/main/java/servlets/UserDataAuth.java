package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

import DB.DBConnectionManager;
import DB.DatabaseConnection;
import Session.SessionManager;
import auth.IdTokenVerifierAndParser;

@WebServlet(name = "userdataauth", urlPatterns = "/UserDataAuth")
public class UserDataAuth extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserDataAuth start");
		ServletContext ctx = request.getServletContext();
    	DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
		try {
			System.out.println("DB:" + dbManager.getConnection().isClosed());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("User name: " + username);
		System.out.println("User password: " + password);
		String returnVal = "";
		String UNKNOWNUSER = "UNKNOWNUSER";
		// DB Stuff
		try {
			Connection con = dbManager.getConnection();

			if (!con.isClosed()) {
				System.out.println("DB Connection is open");
				int userId = dbManager.getUserID(username, password);
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				dbManager.insertSession(userId, request.getSession().getId());
				if ( userId > 0 ) 
				{
					System.out.println("Valid User:" + userId);
					returnVal = userId + ":" + session.getId();
				} else 
				{
					System.out.println("Invalid User:" + userId);
					returnVal = UNKNOWNUSER;
				}
				System.out.println("DB Connection is closed");
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// End DB stuff
		response.setContentType("text/plain;charset=UTF-8");
		ServletOutputStream sout = response.getOutputStream();
		sout.print(returnVal);
		System.out.println("UserDataAuth complete:" + returnVal);
	}


}