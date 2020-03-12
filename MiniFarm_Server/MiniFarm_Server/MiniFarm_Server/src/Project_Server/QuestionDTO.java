package Project_Server;

import java.sql.Date;

public class QuestionDTO {
	private int qNumber;
	private String qTitle;
	private String userId;
	private String qQuestion;
	private Date qDate;	
	
	public QuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QuestionDTO(int qNumber, String qTitle, String userId, String qQuestion, Date qDate) {
		super();
		this.qNumber = qNumber;
		this.qTitle = qTitle;
		this.userId = userId;
		this.qQuestion = qQuestion;
		this.qDate = qDate;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getqQuestion() {
		return qQuestion;
	}

	public void setqQuestion(String qQuestion) {
		this.qQuestion = qQuestion;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	@Override
	public String toString() {
		return "QuestionDTO [qNumber=" + qNumber + ", qTitle=" + qTitle + ", userId=" + userId + ", qQuestion="
				+ qQuestion + ", qDate=" + qDate + "]";
	}

	
	
	
}
