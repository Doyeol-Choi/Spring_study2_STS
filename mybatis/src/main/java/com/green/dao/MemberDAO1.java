package com.green.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.green.db.DBManager;
import com.green.vo.MemberVO;

public class MemberDAO1 {	// bybatis 사용하지 않은 예제

	private MemberDAO1() {}
	
	private static MemberDAO1 dao = new MemberDAO1();
	
	public static MemberDAO1 getInstance() {
		return dao;
	}
	
	// 전체 회원 조회
	public List<MemberVO> selectAll() {
		List<MemberVO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM members";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO mVo = new MemberVO();
				
				mVo.setId(rs.getInt("id"));
				mVo.setEmail(rs.getString("email"));
				mVo.setName(rs.getString("name"));
				mVo.setPassword(rs.getString("password"));
				mVo.setRegisterDate(rs.getDate("regDate"));
				
				list.add(mVo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
