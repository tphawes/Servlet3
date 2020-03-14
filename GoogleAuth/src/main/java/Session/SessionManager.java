package Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import DB.DBConnectionManager;
import DB.DatabaseConnection;

public class SessionManager {
	
	public static void createSession(String username, String sessionId)
	{
		// DB Stuff
		/*
		 * try { //DBConnectionManager Connection con =
		 * DatabaseConnection.initializeDatabase(); if (!con.isClosed()) {
		 * System.out.println("DB Connection is open"); int userId =
		 * 1;//DatabaseConnection.getUserID(username, con); System.out.println("userId:"
		 * + userId);
		 * 
		 * PreparedStatement pstmt; Timestamp currentTimestamp = new
		 * java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		 * 
		 * pstmt = con.
		 * prepareStatement("insert into demoprj.session_tracker (session_id, user_id, create_date) values (?, ?, ?)"
		 * ); // Create a PreparedStatement object 1 pstmt.setString(1, sessionId); //
		 * Assign value to input parameter 2 pstmt.setInt(2, userId); // Assign value to
		 * input parameter 2 pstmt.setTimestamp(3, currentTimestamp); boolean rs =
		 * pstmt.execute(); if (rs) { // Position the cursor 4
		 * System.out.println("Completed insert"); } pstmt.close(); //con.close();
		 * System.out.println("DB Connection is closed"); } } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}

}
