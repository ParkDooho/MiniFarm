package Project_Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SensorDAO {
	private Connection con;

	public SensorDAO(Connection con) {
		super();
		this.con = con;
	}

	// ���� ���� ����
	public void createSensorInfo(SensorDTO nSensor) {
		String sql = "insert into sensor values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nSensor.getUserId().replace("\r\n", ""));
			pstmt.setString(2, nSensor.getSensorName().replace("\r\n", ""));
			pstmt.setFloat(3, nSensor.getSensorTem());
			pstmt.setFloat(4, nSensor.getSensorHum());
			pstmt.setInt(5, nSensor.getSensorIllu());
			pstmt.setFloat(6, nSensor.getSensorPH());
			pstmt.setInt(7, nSensor.getSensorFD());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���� ������ ����");
	}

	// ���� ���� ����
	public void updateSensorInfo(SensorDTO nSensor) {
		String sql = "update sensor set sensorName=?, sensorTem=?, sensorHum=?, sensorIllu=?, sensorPH=?, sensorFd=? where userId=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nSensor.getSensorName().replace("\r\n", ""));
			pstmt.setFloat(2, nSensor.getSensorTem());
			pstmt.setFloat(3, nSensor.getSensorHum());
			pstmt.setInt(4, nSensor.getSensorIllu());
			pstmt.setFloat(5, nSensor.getSensorPH());
			pstmt.setInt(6, nSensor.getSensorFD());
			pstmt.setString(7, nSensor.getUserId().replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���� ������ ����");
	}

	// ���� ���� ����
	public void deleteSensorInfo(String userId, String sensorName) {
		String sql = "delete from sensor  where userId=? and sensorName= ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			pstmt.setString(2, sensorName.replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���� ������ ����");
	}

	// ���� ���� ��ȸ
	@SuppressWarnings("unchecked")
	public JSONObject findSensorInfo(String userId) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from sensor where userId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				SensorDTO temp = new SensorDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setSensorName(rs.getString("sensorName"));
				temp.setSensorTem(rs.getFloat("sensorTem"));
				temp.setSensorHum(rs.getFloat("sensorHum"));
				temp.setSensorIllu(rs.getInt("sensorIllu"));
				temp.setSensorPH(rs.getFloat("sensorPH"));
				temp.setSensorFD(rs.getInt("sensorFD"));
				
				jObject.put("userId", temp.getUserId());
				jObject.put("sensorName", temp.getSensorName());
				jObject.put("sensorTem", temp.getSensorTem());
				jObject.put("sensorHum", temp.getSensorHum());
				jObject.put("sensorIllu", temp.getSensorIllu());
				jObject.put("sensorPH", temp.getSensorPH());
				jObject.put("sensorFD", temp.getSensorFD());
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

	// ��� ���� ���� ��ȸ
	@SuppressWarnings("unchecked")
	public JSONObject showAllSensor() {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from sensor";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				SensorDTO temp = new SensorDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setSensorName(rs.getString("sensorName"));
				temp.setSensorTem(rs.getFloat("sensorTem"));
				temp.setSensorHum(rs.getFloat("sensorHum"));
				temp.setSensorIllu(rs.getInt("sensorIllu"));
				temp.setSensorPH(rs.getFloat("sensorPH"));
				temp.setSensorFD(rs.getInt("sensorFD"));
				
				jObject.put("userId", temp.getUserId());
				jObject.put("sensorName", temp.getSensorName());
				jObject.put("sensorTem", temp.getSensorTem());
				jObject.put("sensorHum", temp.getSensorHum());
				jObject.put("sensorIllu", temp.getSensorIllu());
				jObject.put("sensorPH", temp.getSensorPH());
				jObject.put("sensorFD", temp.getSensorFD());
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
