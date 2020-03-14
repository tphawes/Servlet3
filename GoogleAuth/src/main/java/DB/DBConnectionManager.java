package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DBConnectionManager {

	private String dbURL;
	private String user;
	private String password;
	private String driver;
	private Connection con;
	
	public DBConnectionManager(String url, String u, String p, String d){
		this.dbURL=url;
		this.user=u;
		this.password=p;
		this.driver=d;
		//create db connection now
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			System.out.println("DBConnectionManager Create con");
			this.con = DriverManager.getConnection(this.dbURL, 
															this.user,  
															this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public Connection getConnection(){
		try {
			System.out.println("DBConnectionManager Get con:" + this.con.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.con;
	}
	
	public void closeConnection(){
		//close DB connection here
		try {
			System.out.println("DBConnectionManager close con");

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public Boolean getUser(String userName, String userPassword, Connection con) throws SQLException {
		Boolean retVal = false;
		PreparedStatement pstmt;
		ResultSet rs;
		pstmt = this.con.prepareStatement("SELECT user_name FROM demoprj.user_data WHERE user_name=? and pswd=?");
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
    public int getUserID(String userName) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		int retVal = -1;
		pstmt = this.con.prepareStatement("SELECT id FROM demoprj.user_data WHERE user_name=?");
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
    public int getUserIDFromSessionTracker(String sessionID) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		int retVal = -1;
		pstmt = this.con.prepareStatement("SELECT id FROM demoprj.session_tracker WHERE session_id=?");
		// Create a PreparedStatement object 1
		pstmt.setString(1, sessionID); // Assign value to input parameter 2
		rs = pstmt.executeQuery(); // Get the result table from the query 3
		if (rs.next()) { 
			retVal = rs.getInt(1);// Position the cursor
		}
		rs.close(); // Close the ResultSet 5
		pstmt.close();
		return retVal;
	}
    public int getUserID(String userName, String userPassword) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		int retVal = -1;
		pstmt = this.con.prepareStatement("SELECT id FROM demoprj.user_data WHERE user_name=? and pswd=?");
		// Create a PreparedStatement object 1
		pstmt.setString(1, userName); // Assign value to input parameter 1
		pstmt.setString(2, userPassword); // Assign value to input parameter 2
		rs = pstmt.executeQuery(); // Get the result table from the query 3
		if (rs.next()) { 
			retVal = rs.getInt(1);// Position the cursor
		}
		rs.close(); // Close the ResultSet 5
		pstmt.close();
		return retVal;
	}
    public int getSessionID(String sessionIDIn) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		int retVal = -1;
		pstmt = this.con.prepareStatement("SELECT id FROM demoprj.session_tracker WHERE session_id=?");
		// Create a PreparedStatement object 1
		pstmt.setString(1, sessionIDIn); // Assign value to input parameter 2
		rs = pstmt.executeQuery(); // Get the result table from the query 3
		if (rs.next()) { 
			retVal = rs.getInt(1);// Position the cursor
		}
		rs.close(); // Close the ResultSet 5
		pstmt.close();
		return retVal;
	}
    public void insertSession(int userId, String sessionId)
    {
		try {
			//DBConnectionManager
			if (!this.con.isClosed()) {
				System.out.println("DB Connection is open");
				PreparedStatement pstmt;
				Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
				pstmt = this.con.prepareStatement("insert into demoprj.session_tracker (session_id, user_id, create_date) values (?, ?, ?)");
				// Create a PreparedStatement object 1
				pstmt.setString(1, sessionId); // Assign value to input parameter 2
				pstmt.setInt(2, userId); // Assign value to input parameter 2
				pstmt.setTimestamp(3, currentTimestamp);
				boolean rs = pstmt.execute();
				if (rs) 
				{ // Position the cursor 4
					System.out.println("Completed insert");				
				}
				pstmt.close();
				System.out.println("DB Connection is closed");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void insertResponse(int session_id, String response_string)
    {
		try {
			//DBConnectionManager
			if (!this.con.isClosed()) {
				System.out.println("DB Connection is open");
				PreparedStatement pstmt;
				Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
				//insert into response_data (session_id, response_data, create_date) values(1, 'I feel horrible', NOW());
				pstmt = con.prepareStatement("insert into demoprj.response_data (session_id, response_string, create_date) values (?, ?, ?)");
				// Create a PreparedStatement object 1
				pstmt.setInt(1, session_id); // Assign value to input parameter 2
				pstmt.setString(2, response_string); // Assign value to input parameter 2
				pstmt.setTimestamp(3, currentTimestamp);
				boolean rs = pstmt.execute();
				if (rs) 
				{ // Position the cursor 4
					System.out.println("Completed insert");				
				}
				pstmt.close();
				System.out.println("DB Connection is closed");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
    
    public List<String> getPreviousUserResponses(int userId) throws SQLException {
    	List<String> returnList = new ArrayList<String>();
		PreparedStatement pstmt;
		ResultSet rs;
		String retVal = "";
		pstmt = this.con.prepareStatement("SELECT response_string from response_data where session_id in ( select id from session_tracker where user_id=?)");
		// Create a PreparedStatement object 1
		pstmt.setInt(1, userId); // Assign value to input parameter 2
		rs = pstmt.executeQuery(); // Get the result table from the query 3
		if( !rs.next())
		{
			System.out.println("No results:");
		} else
		{ 
			while(rs.next()  )
			{
				retVal = rs.getString(1);// Position the cursor
				returnList.add(retVal);
				System.out.println("retVal:" + retVal);
			}
		}
		rs.close(); // Close the ResultSet 5
		pstmt.close();
		return returnList;
	}
    
    
}