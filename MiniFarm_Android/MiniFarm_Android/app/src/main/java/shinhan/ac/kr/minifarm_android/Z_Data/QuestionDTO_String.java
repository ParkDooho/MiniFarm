package shinhan.ac.kr.minifarm_android.Z_Data;

import java.sql.Date;

public class QuestionDTO_String {
    private String qNumber;
    private String qTitle;
    private String userId;
    private String qQuestion;
    private String qDate;

    public QuestionDTO_String() {
        super();
        // TODO Auto-generated constructor stub
    }

    public QuestionDTO_String(String qNumber, String qTitle, String userId, String qQuestion, String qDate) {
        super();
        this.qNumber = qNumber;
        this.qTitle = qTitle;
        this.userId = userId;
        this.qQuestion = qQuestion;
        this.qDate = qDate;
    }

    public String getqNumber() {
        return qNumber;
    }

    public void setqNumber(String qNumber) {
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

    public String getqDate() {
        return qDate;
    }

    public void setqDate(String qDate) {
        this.qDate = qDate;
    }

    @Override
    public String toString() {
        return "QuestionDTO [qNumber=" + qNumber + ", qTitle=" + qTitle + ", userId=" + userId + ", qQuestion="
                + qQuestion + ", qDate=" + qDate + "]";
    }








}
