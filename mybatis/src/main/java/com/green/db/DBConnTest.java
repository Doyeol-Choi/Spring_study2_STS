package com.green.db;

import java.sql.Connection;

public class DBConnTest {

	public static void main(String[] args) {
		Connection conn = DBManager.getConnection();
		
		if(conn != null) {
			System.out.println("연결 성공!");
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("연결 실패!");
		}

	}

}
