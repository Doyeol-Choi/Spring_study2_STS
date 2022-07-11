package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.controller.ChangePwdController;
import spring.controller.CommonExceptionHandler;
import spring.controller.LoginController;
import spring.controller.LogoutController;
import spring.controller.MemberDetailController;
import spring.controller.MemberListController;
import spring.controller.RegisterController;
import spring.controller.SurveyController;
import spring.dao.MemberDao;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
public class ControllerConfig {

	// MemberConfig에서 가져오기 위한 어노테이션
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ChangePasswordService changePwdSvc;
	
	@Autowired
	private AuthService authSvc;
	
	@Autowired
	private MemberRegisterService regSvc;
	
	@Bean
	public RegisterController registerController() {
		RegisterController rc = new RegisterController();
		rc.setMemberRegisterService(regSvc);
		return rc;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean
	public LoginController loginController() {
		LoginController lc = new LoginController();
		lc.setAuthService(authSvc);
		return lc;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public ChangePwdController changePwdController() {
		ChangePwdController cpc = new ChangePwdController();
		cpc.setChangePasswordService(changePwdSvc);
		return cpc;
	}
	
	@Bean
	public MemberListController memberListController() {
		MemberListController mlc = new MemberListController();
		mlc.setDao(memberDao);
		return mlc;
	}
	
	@Bean
	public MemberDetailController memberDetailController() {
		MemberDetailController mdc = new MemberDetailController();
		mdc.setDao(memberDao);
		return mdc;
	}
	
	@Bean
	public CommonExceptionHandler commonExceptionHandler() {
		return new CommonExceptionHandler();
	}
}
