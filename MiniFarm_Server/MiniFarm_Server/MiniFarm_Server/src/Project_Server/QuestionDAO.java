package Project_Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuestionDAO {
	private Connection con;

	public QuestionDAO(Connection con) {
		super();
		this.con = con;
	}

	// 질문 생성
	public void createQuestionInfo(QuestionDTO nQuestion) {
		String sql = "insert into question values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setNull(1, Types.INTEGER);
			pstmt.setString(2, nQuestion.getqTitle().replace("\r\n", ""));
			pstmt.setString(3, nQuestion.getUserId().replace("\r\n", ""));
			pstmt.setString(4,nQuestion.getqQuestion().replace("\r\n", ""));
			pstmt.setDate(5, nQuestion.getqDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("질문 데이터 생성");
	}

	// 질문 수정
	public void updateQuestionInfo(QuestionDTO nQuestion) {
		String sql = "update question set qTitle = ?, userId = ?, qQuestion = ?, qDate = ?	where qNumber = ?; qNumber\r\n" + 
				"	Alter table Question drop qNumber;\r\n" + 
				"	ALTER TABLE Question ADD qNumber int primary key auto_increment FIRST;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nQuestion.getqTitle().replace("\r\n", ""));
			pstmt.setString(2, nQuestion.getUserId().replace("\r\n", ""));
			pstmt.setString(3,nQuestion.getqQuestion().replace("\r\n", ""));
			pstmt.setDate(4, nQuestion.getqDate());
			pstmt.setInt(5, nQuestion.getqNumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("질문 데이터 수정");
	}

	// 질문 삭제
	public void deleteQuestionInfo(int qNumber) {
		String sql = "delete from question where qNumber =?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qNumber);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("질문 데이터 삭제");
	}

	// 유저 이름으로 질문 찾기
	@SuppressWarnings("unchecked")
	public JSONObject findQuestionInfo_qNumber(int qNumber) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from question where qNumber=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qNumber);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				QuestionDTO temp = new QuestionDTO();
				JSONObject jObject = new JSONObject();
				temp.setqNumber(rs.getInt("qNumber"));
				temp.setqTitle(rs.getString("qTitle"));
				temp.setUserId(rs.getString("userId"));
				temp.setqQuestion(rs.getString("qQuestion"));
				temp.setqDate(rs.getDate("qDate"));

				jObject.put("qNumber", temp.getqNumber());
				jObject.put("qTitle", temp.getqTitle());
				jObject.put("userId", temp.getUserId());
				jObject.put("qQuestion", temp.getqQuestion());
				jObject.put("qDate", temp.getqDate());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("질문 데이터 조회_질문번호");
		return jsonMain;
	}

	// 유저 ID로 질문 찾기
	@SuppressWarnings("unchecked")
	public JSONObject findQuestionInfo_userId(String userId) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from question where userId=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				QuestionDTO temp = new QuestionDTO();
				JSONObject jObject = new JSONObject();
				temp.setqNumber(rs.getInt("qNumber"));
				temp.setqTitle(rs.getString("qTitle"));
				temp.setUserId(rs.getString("userId"));
				temp.setqQuestion(rs.getString("qQuestion"));
				temp.setqDate(rs.getDate("qDate"));

				jObject.put("qNumber", temp.getqNumber());
				jObject.put("qTitle", temp.getqTitle());
				jObject.put("userId", temp.getUserId());
				jObject.put("qQuestion", temp.getqQuestion());
				jObject.put("qDate", temp.getqDate());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("질문 데이터 조회_아이디");
		return jsonMain;
	}

	// 제목로 질문 찾기
	@SuppressWarnings("unchecked")
	public JSONObject findQuestionInfo_qTitle(String qTitle) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from question where qTitle like ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + qTitle.replace("\r\n", "") + "%");
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				QuestionDTO temp = new QuestionDTO();
				JSONObject jObject = new JSONObject();
				temp.setqNumber(rs.getInt("qNumber"));
				temp.setqTitle(rs.getString("qTitle"));
				temp.setUserId(rs.getString("userId"));
				temp.setqQuestion(rs.getString("qQuestion"));
				temp.setqDate(rs.getDate("qDate"));

				jObject.put("qNumber", temp.getqNumber());
				jObject.put("qTitle", temp.getqTitle());
				jObject.put("userId", temp.getUserId());
				jObject.put("qQuestion", temp.getUserId());
				jObject.put("qDate", temp.getqDate());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("질문 데이터 조회_제목");
		return jsonMain;
	}

	// 모든 질문 조회
	@SuppressWarnings("unchecked")
	public JSONObject showAllQuestion() {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from question";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				QuestionDTO temp = new QuestionDTO();
				JSONObject jObject = new JSONObject();
				temp.setqNumber(rs.getInt("qNumber"));
				temp.setqTitle(rs.getString("qTitle"));
				temp.setUserId(rs.getString("userId"));
				temp.setqQuestion(rs.getString("qQuestion"));
				temp.setqDate(rs.getDate("qDate"));

				jObject.put("qNumber", temp.getqNumber());
				jObject.put("qTitle", temp.getqTitle());
				jObject.put("userId", temp.getUserId());
				jObject.put("qQuestion", temp.getUserId());
				jObject.put("qDate", temp.getqDate());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("모든 질문 데이터 풀력");
		return jsonMain;
	}
	@SuppressWarnings("unchecked")
	public JSONObject Question_Join_Answer(String userId) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from (select Q.qNumber, Q.qTitle, Q.qQuestion, Q.userId, Q.qDate, A.aNumber, A.aAnswer from Question As Q LEFT OUTER JOIN Answer As A ON Q.userId = A.userId)CNT where userId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				Question_Join_AnswerDTO temp = new Question_Join_AnswerDTO();
				JSONObject jObject = new JSONObject();
				temp.setqNumber(rs.getInt("qNumber"));
				temp.setqTitle(rs.getString("qTitle"));
				temp.setqQuestion(rs.getString("qQuestion"));
				temp.setUserId(rs.getString("userId"));
				temp.setqDate(rs.getDate("qDate"));
				temp.setaNumber(rs.getInt("aNumber"));
				temp.setaAnswer(rs.getString("aAnswer"));

				jObject.put("qNumber", temp.getqNumber());
				jObject.put("qTitle", temp.getqTitle());
				jObject.put("qQuestion", temp.getqQuestion());
				jObject.put("userId", temp.getUserId());
				jObject.put("qDate", temp.getqDate());
				jObject.put("aNumber", temp.getaNumber());
				jObject.put("aAnswer", temp.getaAnswer());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("질문_답변_조인결과_아이디");
		return jsonMain;
	}
}
