package spring.service;

import spring.dao.MemberDao;
import spring.exception.IdPasswordNotMatchingException;
import spring.vo.AuthInfo;
import spring.vo.Member;

public class AuthService {
	
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenicate(String email, String password) {	// 로그인처리를 위한 기능 메서드
		Member member = memberDao.selectByEmail(email);
		
		if(member==null) {
			throw new IdPasswordNotMatchingException();
		}
		
		if(!member.getPassword().equals(password)) {
			throw new IdPasswordNotMatchingException();
		}
		
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
}
