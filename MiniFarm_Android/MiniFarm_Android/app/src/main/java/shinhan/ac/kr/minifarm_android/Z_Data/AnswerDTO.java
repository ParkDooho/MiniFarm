package shinhan.ac.kr.minifarm_android.Z_Data;

public class AnswerDTO {
    private int qNumber;
    private int aNumber;
    private String aAnswer;
    private String userId;

    public AnswerDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AnswerDTO(int qNumber, int aNumber, String aAnswer, String userId) {
        super();
        this.qNumber = qNumber;
        this.aNumber = aNumber;
        this.aAnswer = aAnswer;
        this.userId = userId;
    }

    public int getqNumber() {
        return qNumber;
    }
    public void setqNumber(int qNumber) {
        this.qNumber = qNumber;
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
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AnswerDTO [qNumber=" + qNumber + ", aNumber=" + aNumber + ", aAnswer=" + aAnswer + ", userId=" + userId
                + "]";
    }



}
