package spring.service;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

public class ChangePasswordService {
	
//	private MemberDao dao = new MemberDao();
	
	private MemberDao dao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.dao = memberDao;	// 생성자를 통해 주입받는다. DI
	}
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = dao.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPwd, newPwd);
		
		dao.updateMember(member);
	}
	
}
