package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.AuthService;
import spring.validator.LoginCommandValidator;
import spring.vo.LoginCommand;

@Controller
public class LoginController {
	
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("login")
	public String form(Model model) {
		model.addAttribute("loginCommand", new LoginCommand());
		return "login/loginForm";
	}
	
	@PostMapping("login")
	public String submit(@ModelAttribute("loginCommand")LoginCommand loginCommand, Errors err) {
		
		new LoginCommandValidator().validate(loginCommand, err);
		
		if(err.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			authService.authenicate(loginCommand.getEmail(), loginCommand.getPassword());
			return "login/loginSuccess";			
		} catch(IdPasswordNotMatchingException e) {
			err.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
		
	}
}
