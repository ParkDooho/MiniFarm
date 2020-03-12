package Project_Server;

import java.sql.Date;

public class Question_Join_AnswerDTO {
	private int qNumber;
	private String qTitle;
	private String qQuestion;
	private String userId;
	private Date qDate;
	private int aNumber;
	private String aAnswer;
	public Question_Join_AnswerDTO(int qNumber, String qTitle, String qQuestion, String userId, Date qDate, int aNumber,
			String aAnswer) {
		super();
		this.qNumber = qNumber;
		this.qTitle = qTitle;
		this.qQuestion = qQuestion;
		this.userId = userId;
		this.qDate = qDate;
		this.aNumber = aNumber;
		this.aAnswer = aAnswer;
	}
	public Question_Join_AnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getqNumber() {
		return qNumber;
	}
	public void setqNumber(int qNumber) {
		this.qNumber = qNumber;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqQuestion() {
		return qQuestion;
	}
	public void setqQuestion(String qQuestion) {
		this.qQuestion = qQuestion;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getqDate() {
		return qDate;
	}
	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}
	public int getaNumber() {
		return aNumber;
	}
	public void setaNumber(int aNumber) {
		this.aNumber = aNumber;
	}
	public String getaAnswer() {
		return aAnswer;
	}
	public void setaAnswer(String aAnswer) {
		this.aAnswer = aAnswer;
	}
	@Override
	public String toString() {
		return "Question_Join_AnswerDTO [qNumber=" + qNumber + ", qTitle=" + qTitle + ", qQuestion=" + qQuestion
				+ ", userId=" + userId + ", qDate=" + qDate + ", aNumber=" + aNumber + ", aAnswer=" + aAnswer + "]";
	}
	
	
}
