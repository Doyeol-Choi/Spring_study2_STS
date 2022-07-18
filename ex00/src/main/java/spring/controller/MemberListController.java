package spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import spring.dao.MemberDao;
import spring.vo.ListCommand;
import spring.vo.Member;

@Controller
public class MemberListController {

	private MemberDao dao;	// service 클래스 없이 바로 호출, 좋은 방법은 아님
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	
	// RequestMapping방법과 get, post를 나누는 방법
	@GetMapping("/member/list")
	public String form(ListCommand listCommand) {
		return "member/memberList";
	}
	
	@PostMapping("/member/list")
	public String list(ListCommand listCommand, Model model) {
		
		if(listCommand.getFrom() != null && listCommand.getTo() != null) {
			// 날짜 데이터가 존재 할때만 조회한 데이터를 보낸다.
			List<Member> list = dao.selectByRegDate(listCommand);
			
			model.addAttribute("members", list);
		}
		
		return "member/memberList";
	}
}
