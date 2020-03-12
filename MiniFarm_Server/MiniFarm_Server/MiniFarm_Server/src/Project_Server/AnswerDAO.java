package Project_Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AnswerDAO {

	private Connection con;

	public AnswerDAO(Connection con) {
		super();
		this.con = con;
	}

	// 답변 생성
	public void createAnswerInfo(AnswerDTO nAnswer) {
		String sql = "insert into answer values(?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nAnswer.getqNumber());
			pstmt.setNull(2, Types.INTEGER);
			pstmt.setString(3, nAnswer.getaAnswer().replace("\r\n", ""));
			pstmt.setString(4, nAnswer.getUserId().replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("댓글 데이터 생성");
	}

	// 답변 수정
	public void updateAnswerInfo(AnswerDTO nAnswer) {
		String sql = "update answer set qNumber = ?, aNumber= ?, aAnswer = ?, userId = ? where qNumber = ? and aNumber = ?; aNumber\r\n" + 
				"	Alter table Answer drop aNumber;\r\n" + 
				"	ALTER TABLE Answer ADD aNumber int primary key auto_increment FIRST;";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nAnswer.getqNumber());
			pstmt.setInt(2, nAnswer.getaNumber());
			pstmt.setString(3, nAnswer.getaAnswer().replace("\r\n", ""));
			pstmt.setString(4, nAnswer.getUserId().replace("\r\n", ""));
			pstmt.setInt(5, nAnswer.getqNumber());
			pstmt.setInt(6, nAnswer.getaNumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("댓글 데이터 수정");
	}

	// 답변 삭제
	public void deleteAnswerInfo(int qNumber, int aNumber) {
		String sql = "delete from answer where qNumber = ? and aNumber = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qNumber);
			pstmt.setInt(2, aNumber);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("댓글 데이터 삭제");
	}

	// 질문 번호 답변 찾기
	@SuppressWarnings("unchecked")
	public JSONObject findAnswerInfo(int qNumber) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from answer where qNumber=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qNumber);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				AnswerDTO temp = new AnswerDTO();
				JSONObject jObject = new JSONObject();
				temp.setqNumber(rs.getInt("qNumber"));
				temp.setaNumber(rs.getInt("aNumber"));
				temp.setaAnswer(rs.getString("aAnswer"));
				temp.setUserId(rs.getString("userId"));

				jObject.put("qNumber", temp.getqNumber());
				jObject.put("aNumber", temp.getaNumber());
				jObject.put("aAnswer", temp.getaAnswer());
				jObject.put("userId", temp.getUserId());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("댓글 데이터 조회_질문번호");
		return jsonMain;
	}
	
}
