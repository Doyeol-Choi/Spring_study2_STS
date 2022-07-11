package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.ChangePasswordService;
import spring.validator.ChangePwdCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.ChangePwdCommand;

@Controller
@RequestMapping("/edit")
public class ChangePwdController {
	// 비밀번호 변경시 서비스 객체의 메서드를 사용하기위해 주입해놓기
	private ChangePasswordService changePasswordService;

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	// 1. Model 객체를 이용하는 방법
//	@GetMapping("/changePassword")
//	public String form(Model model) {
//		model.addAttribute("changePwdCommand", new ChangePwdCommand());
//		return "edit/changePwdForm";
//	}
	
	// 2. @ModelAttribute 어노테이션을 이용하는 방법
//	@GetMapping("/changePassword")
//	public String form(@ModelAttribute("changePwdCommand")ChangePwdCommand changePwdCommand) {
//
//		return "edit/changePwdForm";
//	}
	

	// 3. post하고 같은 커맨드 객체를 사용하는 경우 => @ModelAttribute를 생략할 수 있다.
	@GetMapping("/changePassword")
	public String form(ChangePwdCommand changePwdCommand, HttpSession session) {
//		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");	
//		// 로그인을 안한 사람이 바로 비밀번호 변경 페이지로 접근할 경우 로그인 페이지로 이동
//		if(authInfo == null) {
//			return "redirect:/login";
//		}
		
		return "edit/changePwdForm";
	}
	
	@PostMapping("/changePassword")
	public String submit(ChangePwdCommand changePwdCommand, Errors error, HttpSession session) {
		
		new ChangePwdCommandValidator().validate(changePwdCommand, error);
		
		if(error.hasErrors()) {
			return "edit/changePwdForm";
		}
		// 비밀번호 변경시 필요한 이메일 정보를 가져오기 위해 세션에서 호출
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");	
		
		try {
			// 비밀번호 변경을 위해 서비스객체에서 메서드 호출
			changePasswordService.changePassword(authInfo.getEmail(), changePwdCommand.getCurrentPassword(), changePwdCommand.getNewPassword());
			
			return "edit/changedPwd";
		} catch(IdPasswordNotMatchingException err) {	// 기존 비밀번호가 틀렸을때 오류 발생
			error.rejectValue("currentPassword", "notmatching");
			return "edit/changePwdForm";
		}
	}
}
