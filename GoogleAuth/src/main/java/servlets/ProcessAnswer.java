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
import java.util.ListIterator;

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
import Model.DialogData;
import Model.SessionData;
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
		String questionForUser = request.getParameter("userQuestion");
		
		System.out.println("User userAnswer: " + userAnswer);
		System.out.println("User questionForUser: " + questionForUser);

		String returnVal = "We may need to look a little deeper";
		// DB Stuff
		try {
			ServletContext ctx = request.getServletContext();
	    	DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
	    	//Need userID to get previous answers
	    	SessionData sd = (SessionData) request.getSession().getAttribute("sessionDataObject");
	    	sd.addDialogData(userAnswer, questionForUser, ctx);
			System.out.println("We have some things to consider:" + sd.getDialogDatas().size());
			//Now execute some logic to get the next question/response
			
		} catch (Exception e) {
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