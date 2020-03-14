package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import DB.DatabaseConnection;
import Session.SessionManager;
import auth.IdTokenVerifierAndParser;

@WebServlet(name = "processanswer", urlPatterns = "/ProcessAnswer")
public class ProcessAnswer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ProcessAnswer start");
		String userAnswer = request.getParameter("userAnswer");
		System.out.println("User userAnswer: " + userAnswer);
		String returnVal = "We may need to look a little deeper";
		// DB Stuff
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			if (!con.isClosed()) {
				System.out.println("DB Connection is open");
				String sessionId = DatabaseConnection.getSessionID(request.getSession(true).getId(), con);
				PreparedStatement pstmt;
				Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
				//insert into response_data (session_id, response_data, create_date) values(1, 'I feel horrible', NOW());
				pstmt = con.prepareStatement("insert into demoprj.response_data (session_id, response_data, create_date) values (?, ?, ?)");
				// Create a PreparedStatement object 1
				pstmt.setString(1, sessionId); // Assign value to input parameter 2
				pstmt.setString(2, userAnswer); // Assign value to input parameter 2
				pstmt.setTimestamp(3, currentTimestamp);
				boolean rs = pstmt.execute();
				if (rs) 
				{ // Position the cursor 4
					System.out.println("Completed insert");				
				}
				pstmt.close();
				con.close();
				System.out.println("DB Connection is closed");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// End DB stuff
		response.setContentType("text/plain;charset=UTF-8");
		ServletOutputStream sout = response.getOutputStream();
		sout.print(returnVal);
		System.out.println("ProcessAnswer complete:" + returnVal);
	}


}