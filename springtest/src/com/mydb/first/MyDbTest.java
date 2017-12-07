package com.mydb.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from ap";

		try {
			
			//jvm ���Ҳ������ࣨ������ 
			Class.forName("com.mysql.jdbc.Driver");
			
			//ͨ��������������ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud_platform", "root", "feixun*123");

			//���� statement
			st = conn.createStatement();

			//ִ��SQL���
			rs = st.executeQuery(sql);

			//�����صĽ��
			while(rs.next()){
				System.out.println(rs.getString("sn"));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//��Դ�ͷ�
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
