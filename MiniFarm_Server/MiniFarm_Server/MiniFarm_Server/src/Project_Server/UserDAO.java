package Project_Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserDAO {

	private Connection con;

	public UserDAO(Connection con) {
		super();
		this.con = con;
	}

// 유저 생성
	public void createUserInfo(UserDTO nUser) {
		String sql = "insert into user values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nUser.getUserId().replace("\r\n", ""));
			pstmt.setString(2, nUser.getUserPw().replace("\r\n", ""));
			pstmt.setString(3, nUser.getUserName().replace("\r\n", ""));
			pstmt.setInt(4, nUser.getUserQuestion());
			pstmt.setString(5, nUser.getUserAnswer().replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("유저 데이터 생성");
	}

// 유저 정보 수정
	public void updateUserInfo(UserDTO nUser) {
		String sql = "update user set userId=?, userPw=?, userName=?, userQuestion=?, userAnswer=? where userId=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nUser.getUserId().replace("\r\n", ""));
			pstmt.setString(2, nUser.getUserPw().replace("\r\n", ""));
			pstmt.setString(3, nUser.getUserName().replace("\r\n", ""));
			pstmt.setInt(4, nUser.getUserQuestion());
			pstmt.setString(5, nUser.getUserAnswer().replace("\r\n", ""));
			pstmt.setString(6, nUser.getUserId().replace("\r\n", ""));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("유저 데이터 수정");
	}

// 유저 정보 삭제
	public void deleteUserInfo(String userId, String userPw) {
		String sql = "delete from user where userId=? and userPw=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			pstmt.setString(2, userPw.replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("유저 데이터 삭제");
	}

//로그인
	@SuppressWarnings("unchecked")
	public JSONObject userLogin(String userId, String userPw) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select userId, userPw from user where userId=? and userPw=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			pstmt.setString(2, userPw.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				UserDTO temp = new UserDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setUserPw(rs.getString("userPw"));

				jObject.put("userId", temp.getUserId());
				jObject.put("userPw", temp.getUserPw());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("유저 로그인_아이디,패스워드");
		return jsonMain;
	}

//유저 ID로 정보 찾기
	@SuppressWarnings("unchecked")
	public JSONObject findUserInfo_userId(String userId) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from user where userId=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				UserDTO temp = new UserDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setUserPw(rs.getString("userPw"));
				temp.setUserName(rs.getString("userName"));
				temp.setUserQuestion(rs.getInt("userQuestion"));
				temp.setUserAnswer(rs.getString("userAnswer"));

				jObject.put("userId", temp.getUserId());
				jObject.put("userPw", temp.getUserPw());
				jObject.put("userName", temp.getUserName());
				jObject.put("userQuestion", temp.getUserQuestion());
				jObject.put("userAnswer", temp.getUserAnswer());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("유저 데이터 조회_아이디");
		return jsonMain;
	}

//유저 이름으로 정보 찾기
	@SuppressWarnings("unchecked")
	public JSONObject findUserInfo_userName(String userName) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from user where userName=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				UserDTO temp = new UserDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setUserPw(rs.getString("userPw"));
				temp.setUserName(rs.getString("userName"));
				temp.setUserQuestion(rs.getInt("userQuestion"));
				temp.setUserAnswer(rs.getString("userAnswer"));

				jObject.put("userId", temp.getUserId());
				jObject.put("userPw", temp.getUserPw());
				jObject.put("userName", temp.getUserName());
				jObject.put("userQuestion", temp.getUserQuestion());
				jObject.put("userAnswer", temp.getUserAnswer());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("유저 데이터 조회_이름");
		return jsonMain;
	}

//비밀번호찾기
	@SuppressWarnings("unchecked")
	public JSONObject findUserPw(String userId, String userName, int userQuestion, String userAnswer) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select userPw from user where userId=? and userName=? and userQuestion=? and userAnswer=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			pstmt.setString(2, userName.replace("\r\n", ""));
			pstmt.setInt(3, userQuestion);
			pstmt.setString(4, userAnswer.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				UserDTO temp = new UserDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserPw(rs.getString("userPw"));

				jObject.put("userPw", temp.getUserPw());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("비밀번호 찾기");
		return jsonMain;
	}

	
//모든 사용자 정보 조회
	@SuppressWarnings("unchecked")
	public JSONObject showAllUser() {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from user";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				UserDTO temp = new UserDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setUserPw(rs.getString("userPw"));
				temp.setUserName(rs.getString("userName"));
				temp.setUserQuestion(rs.getInt("userQuestion"));
				temp.setUserAnswer(rs.getString("userAnswer"));

				jObject.put("userId", temp.getUserId());
				jObject.put("userPw", temp.getUserPw());
				jObject.put("userName", temp.getUserName());
				jObject.put("userQuestion", temp.getUserQuestion());
				jObject.put("userAnswer", temp.getUserAnswer());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("모든 유저 데이터 출력");
		return jsonMain;
	}

}
