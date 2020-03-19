package Model;

import java.sql.Timestamp;

public class DialogData {
	private int responseId;
	private String question;
	private String answer;
	private Timestamp dateValue;
	private String sessionId;

	/*
	 * 
	 */
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
}
