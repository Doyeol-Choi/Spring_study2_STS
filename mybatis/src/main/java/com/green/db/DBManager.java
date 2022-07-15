package com.green.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {

	public static Connection getConnection() {
		Connection conn = null;
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uId = "green";
		String uPwd = "1234";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uId, uPwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
