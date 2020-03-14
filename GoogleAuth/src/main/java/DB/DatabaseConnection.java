package DB;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
  
// This class can be used to initialize the database connection 
public class DatabaseConnection { 
    public static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        // Initialize all the information regarding 
        // Database Connection 
        String dbDriver = "com.mysql.cj.jdbc.Driver"; 
        String dbURL = "jdbc:mysql:// localhost:3306/"; 
        // Database name to access 
        String dbName = "demoprj"; 
        String dbUsername = "root"; 
        String dbPassword = "Bwk802!"; 
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL + dbName, 
                                                     dbUsername,  
                                                     dbPassword); 
        return con; 
    }
    public static String getThis(String requestIn)
    {
    	String retVal = "";
    	
    	return retVal;
    }
    public static Boolean getUser(String userName, String userPassword, Connection con) throws SQLException {
		Boolean retVal = false;
		PreparedStatement pstmt;
		ResultSet rs;
		pstmt = con.prepareStatement("SELECT user_name FROM demoprj.user_data WHERE user_name=? and pswd=?");
		// Create a PreparedStatement object 1
		pstmt.setString(1, userName); // Assign value to input parameter 2
		pstmt.setString(2, userPassword); // Assign value to input parameter 2
		rs = pstmt.executeQuery(); // Get the result table from the query 3
		if (rs.next()) { // Position the cursor 4
			retVal = true;
		}
		rs.close(); // Close the ResultSet 5
		pstmt.close();
		return retVal;
	}
    public static int getUserID(String userName, Connection con) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		int retVal = -1;
		pstmt = con.prepareStatement("SELECT id FROM demoprj.user_data WHERE user_name=?");
		// Create a PreparedStatement object 1
		pstmt.setString(1, userName); // Assign value to input parameter 2
		rs = pstmt.executeQuery(); // Get the result table from the query 3
		if (rs.next()) { 
			retVal = rs.getInt(1);// Position the cursor
		}
		rs.close(); // Close the ResultSet 5
		pstmt.close();
		return retVal;
	}
} 