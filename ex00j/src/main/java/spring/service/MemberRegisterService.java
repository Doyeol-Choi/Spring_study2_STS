package spring.service;

import java.util.Date;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistingMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService {	// 회원 가입 기능
	
//	private MemberDao dao = new MemberDao();	// 의존 객체를 직접 생성
	
	private MemberDao dao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.dao = memberDao;	// 생성자를 통해 주입받는다. DI
	}
	
	public void regist(RegisterRequest req) {	// 회원 가입 기능 => 을 사용하기 위해 아래 2개 이상의 다른클래스의 메서드가 필요하다 => 의존 관계
		Member member = dao.selectByEmail(req.getEmail());	// 기존 가입자 조회
		
		if(member != null) {
			throw new AlreadyExistingMemberException("중복 : " + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		
		dao.insertMember(newMember);	// 신규 회원 저장
	}
}
