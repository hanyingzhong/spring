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
		String sql = "select * from cabinet";

		try {
			
			//jvm 查找并加载类（驱动） 
			Class.forName("com.mysql.jdbc.Driver");
			
			//通过驱动管理器获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exchange", "root", "admin");

			//创建 statement
			st = conn.createStatement();

			//执行SQL语句
			rs = st.executeQuery(sql);

			//处理返回的结果
			while(rs.next()){
				System.out.println(rs.getString("cabinetID"));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//资源释放
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
