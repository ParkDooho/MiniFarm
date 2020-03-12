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

// ���� ����
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
		System.out.println("���� ������ ����");
	}

// ���� ���� ����
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
		System.out.println("���� ������ ����");
	}

// ���� ���� ����
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
		System.out.println("���� ������ ����");
	}

//�α���
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
		System.out.println("���� �α���_���̵�,�н�����");
		return jsonMain;
	}

//���� ID�� ���� ã��
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
		System.out.println("���� ������ ��ȸ_���̵�");
		return jsonMain;
	}

//���� �̸����� ���� ã��
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
		System.out.println("���� ������ ��ȸ_�̸�");
		return jsonMain;
	}

//��й�ȣã��
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
		System.out.println("��й�ȣ ã��");
		return jsonMain;
	}

	
//��� ����� ���� ��ȸ
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
		System.out.println("��� ���� ������ ���");
		return jsonMain;
	}

}
