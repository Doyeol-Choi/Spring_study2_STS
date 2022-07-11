package spring.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

@Controller
public class MemberDetailController {
	
	private MemberDao dao;
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@GetMapping("member/detail/{id}")
	public String detail(@PathVariable("id") Long memberId, Model model) {
		// 특정 누군가의 정보를 보고싶은 기능
		// 필요한 정보(누군가를 식별할 수 있는 정보) id => memberId
		// 보여주고 싶은 정보는 누군가의 정보 member => Model
		
		Member member = dao.selectById(memberId);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute("member", member);
		
		return "member/memberDetail";
	}
	
//	@ExceptionHandler(TypeMismatchException.class)
//	public String handlerTypeMismatchException(TypeMismatchException err) {
//		// 형변환 오류(Long 타입이 아닐때)
//		return "member/ivalidid";
//	}
	
//	@ExceptionHandler(MemberNotFoundException.class)
//	public String handlerNotFoundException(MemberNotFoundException err) {
//		// 없는 id조회 오류
//		return "member/noMember";
//	}
	
}
