package com.green.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.green.vo.MemberVO;

public class MemberDAO2 {	// mybatis 설정을 읽어서 DB에 접속할 DAO
	
	private MemberDAO2() {}
	
	private static MemberDAO2 dao = new MemberDAO2();
	
	public static MemberDAO2 getInstance() {
		return dao;
	}

	// 마이바티스를 사용하려면 SqlSessionFactory 객체를 빌드해야 한다.
	private static SqlSessionFactory sqlMapper = null;
	
	private static SqlSessionFactory getFactory() {
		if(sqlMapper == null) {
			try {
				String resource = "com/green/mybatis/mybatisConfig.xml";
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return sqlMapper;
	}
	
	// 마이바티스를 이용해 회원 조회
	public List<MemberVO> selectAll() {
		List<MemberVO> lists = null;
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		lists = session.selectList("com.green.mapper.member.selectAll");
		
		return lists;
	}
	
	// 마이바티스를 이용해 회원수 조회
	public int selectCnt() {
		int cnt = 0;
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		cnt = session.selectOne("com.green.mapper.member.selectCnt");
		
		return cnt;
	}
	
	// 마이바티스를 이용해 회원을 이메일로 조회
	public MemberVO selectByEmail(String email) {
		MemberVO mVo = null;
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		mVo = session.selectOne("com.green.mapper.member.selectByEmail", email);
		
		return mVo;
	}
	
	// 마이바티스를 이용해 회원 등록
	public int insertMember(MemberVO mVo) {
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		int num = session.insert("com.green.mapper.member.insertMember", mVo);
		session.commit();
		
		return num;
	}
	
	// 마이바티스를 이용해 회원 수정
	public int updateMember(MemberVO mVo) {

		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		int num = session.update("com.green.mapper.member.updateMember", mVo);
		session.commit();
		
		return num;
	}
		
	// 마이바티스를 이용해 회원 삭제
	public int deleteMember(String email) {

		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		int num = session.delete("com.green.mapper.member.deleteMember", email);
		session.commit();
		
		return num;
	}
}
