package shinhan.ac.kr.minifarm_android.Z_Data;

public class Question_Join_AnswerDTO {
    private String qNumber;
    private String qTitle;
    private String qQuestion;
    private String userId;
    private String qDate;
    private String aNumber;
    private String aAnswer;
    public Question_Join_AnswerDTO(String qNumber, String qTitle, String qQuestion, String userId, String qDate, String aNumber,
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
    public String getqDate() {
        return qDate;
    }
    public void setqDate(String qDate) {
        this.qDate = qDate;
    }
    public String getaNumber() {
        return aNumber;
    }
    public void setaNumber(String aNumber) {
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
