package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.AlreadyExistingMemberException;
import spring.service.MemberRegisterService;
import spring.validator.RegisterRequestValidator;
import spring.vo.RegisterRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	@RequestMapping("/step1")		// localhost:8085/ex00/register/step1
	public String handlerStep1() {
		return "register/step1";		// WEB-INF/views/register/step1.jsp 를 찾아가도록
	}
	
////	@RequestMapping(value="/register/step2", method=RequestMethod.POST)		// 속성을 2가지 이상 넣어줄땐 확실하게 선언해준다.
//	@PostMapping("/register/step2")		// post방식의 맵핑을 PostMapping 어노테이션으로 사용해 줄 수도 있다. (스프링 5.버전 이상부터 사용가능)
//	public String handlerStep2(HttpServletRequest request) {
//		String agreeParam = request.getParameter("agree");
//		
//		if(agreeParam == null || !agreeParam.equals("true")) {
//			return "register/step1";
//		}
//		
//		return "register/step2";
//	}
	
	// 클라이언트에서 값 얻어오기 2번 방법
	@PostMapping("/step2")
	public String handlerStep2(@RequestParam(value="agree", required=false, defaultValue="false")boolean agree, Model model) {	
		// @RequsetParam은 1번 방법과 달리 자동으로 형태변환 해줄 수 있다.
		if(!agree) {
			return "redirect:step1";
		}
		// form 커스텀 태그의 오류 제어
		model.addAttribute("formData", new RegisterRequest());
		
		return "register/step2";
	}
	
	@GetMapping("/step2")
	public String handlerStep2Get() {	// 주소창에서 step2를 입력해서 페이지로 들어오는 것을 방지
//		return "register/step1";	// 이것만 쓰면 주소창에는 step2로 보여지게 된다. 실제 보여지는 페이지는 step1
		return "redirect:step1";	// 주소창의 요청 경로를 register/step1으로 지정
	}
	
//	@PostMapping("/step3")
//	public String handlerStep3(HttpServletRequest request) {
//		String email = request.getParameter("email");
//		
//		return "register/step3";
//	}
	
	@PostMapping("/step3")
	public String handlerStep3(@ModelAttribute("formData")RegisterRequest regReq, Errors err) {	//	setter 메서드 기준으로 받아온다.
//								@RequestParam("email") String email,
//							    @RequestParam("name") String name,
//							    @RequestParam("password") String password,
//							    @RequestParam("confirmPassword") String confirmPassword
//							   ) {
//		System.out.println("이름 : " + regReq.getName());
//		System.out.println("이메일 : " + regReq.getEmail());
//		System.out.println("암호 : " + regReq.getPassword());
//		System.out.println("암호 확인 : " + regReq.getConfirmPassword());
		
		// 커멘드 객체 값을 검증
		// 검증 후 에러가 있는지 체크(에러코드가 나온게 있는지 체크)
		new RegisterRequestValidator().validate(regReq, err);
		// 서블릿에서 만들어 넣어줄수 있는 Errors를 매개변수로 추가해서 jsp에서 확인한다.
		
		if(err.hasErrors()) {	// 에러가 있는지 체크
			return "register/step2";
		}
		
		try {
			// memberRegisterService의 regist메서드를 통해 DB에 저장
			memberRegisterService.regist(regReq);
			return "register/step3";			
		} catch(AlreadyExistingMemberException e) {
			err.rejectValue("email", "duplicate");	// 중복 에러
			return "register/step2";	// redirect를 쓰면 step2에 get방식으로 접근을 막아놨기때문에 step1로 간다.
		}
			
	}
	
}
