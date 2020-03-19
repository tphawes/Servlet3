package Model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import DB.DBConnectionManager;

public class SessionData {
	private Timestamp dateValue;
	private String sessionId;
	private int userId;
	private List<DialogData> dialogDatas;
	private boolean userTerminated;
	private boolean sessionTimedOut;
	private String nextQuestion;
	public SessionData()
	{
		this.dateValue = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	}
	public void updateSession(int userId, String sessionId, ServletContext ctx)
	{
		this.sessionId = sessionId;
		this.userId = userId;
    	DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
		dbManager.insertSession(userId, sessionId);
		try {
			this.setDialogDatas(dbManager.getPreviousUserResponses(userId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDialogData(String userAnswer, String questionForUser, ServletContext ctx)
	{
		DialogData dialogData = new DialogData();
		if( this.dialogDatas == null )
			this.dialogDatas = new ArrayList<DialogData>();
		this.dialogDatas.add(dialogData);
    	DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
		dbManager.insertResponse(this.getSessionId(), userAnswer, questionForUser);
	}
	public Timestamp getDateValue() {
		return dateValue;
	}
	public void setDateValue(Timestamp dateValue) {
		this.dateValue = dateValue;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<DialogData> getDialogDatas() {
		return dialogDatas;
	}
	public void setDialogDatas(List<DialogData> dialogDatas) {
		this.dialogDatas = dialogDatas;
	}
	public boolean isUserTerminated() {
		return userTerminated;
	}
	public void setUserTerminated(boolean userTerminated) {
		this.userTerminated = userTerminated;
	}
	public boolean isSessionTimedOut() {
		return sessionTimedOut;
	}
	public void setSessionTimedOut(boolean sessionTimedOut) {
		this.sessionTimedOut = sessionTimedOut;
	}
	public String getNextQuestion() {
		return nextQuestion;
	}
	public void setNextQuestion(String nextQuestion) {
		this.nextQuestion = nextQuestion;
	}
}

