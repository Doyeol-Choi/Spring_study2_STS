package spring.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.exception.MemberNotFoundException;

@ControllerAdvice("spring.controller")	// 패키지를 적어준다.
public class CommonExceptionHandler {	// 작동원리 AOP
	
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handlerTypeMismatchException(TypeMismatchException err) {
		// 형변환 오류(Long 타입이 아닐때)
		return "member/ivalidid";
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public String handlerMemberNotFoundException(MemberNotFoundException err) {
		// 없는 id조회 오류
		return "member/noMember";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException(RuntimeException err) {
		return "error/commonException";
	}
}
