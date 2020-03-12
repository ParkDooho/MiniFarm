package shinhan.ac.kr.minifarm_android.Z_Data;

public class UserDTO {
    private String userId;
    private String userPw;
    private String userName;
    private int userQuestion;
    private String userAnswer;

    public UserDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDTO(String userId, String userPw, String userName, int userQuestion, String userAnswer) {
        super();
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userQuestion = userQuestion;
        this.userAnswer = userAnswer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(int userQuestion) {
        this.userQuestion = userQuestion;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userQuestion="
                + userQuestion + ", userAnswer=" + userAnswer + "]";
    }

}
