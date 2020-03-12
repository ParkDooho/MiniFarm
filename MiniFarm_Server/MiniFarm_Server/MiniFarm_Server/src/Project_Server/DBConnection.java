package Project_Server;

import java.sql.*;

public class DBConnection {
	static{
		try {
			Class.forName("com.mysql.jabc.Driver");
		} catch (ClassNotFoundException e) {
		}
	}
	
	public static Connection openConnection(){
		Connection con = null;
		try {
			String url = "jdbc:mysql://localhost:3306/minifarm_db?useSSL=false";	
			String id = "root";
			String passwd = "1234";
			con = DriverManager.getConnection(url,id,passwd);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (SQLException e) {
		}
		
		return con;
	}
	public static void closeConnection(Connection con){
		if(con != null){
			try {
				con.close();
				System.out.println("�����ͺ��̽� ���� ����");
				System.out.println("--------------------------");
			} catch (SQLException e) {
			}
		}
	}
}