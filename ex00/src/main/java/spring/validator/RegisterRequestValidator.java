package spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.vo.RegisterRequest;

public class RegisterRequestValidator implements Validator {

	// 파라미터로 전달받은 객체가 RegisterRequest로 변환 가능한지 확인하는 메서드 
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}
	
	// 이메일 정규식을 이용한 패턴 체크
	private static final String emailExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	
	private Pattern pattern = Pattern.compile(emailExp);
	
	// 검증 하는 메서드 오버라이드
	@Override
	public void validate(Object target, Errors errors) {	// target : 검사 대상 객체, errors : 검사 결과 에러 코드
		// 검사 대상 객체의 특정 프로퍼티의 값이나 상태등이 올바른지 체크하고 값이 올바르지 않다면 errors에 에러코드를 저장
		RegisterRequest regReq = (RegisterRequest)target;
		
//		if(target instanceof RegisterRequest) {	// 위에 형변환 검증 메서드가 없었다면 이와같이 형변환 가능한지 체크후 변환하는게 좋다.
//			RegisterRequest regReq = (RegisterRequest)target;
//		}
		
		// regReq의 email값이 없으면 에러메세지
		if(regReq.getEmail()==null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {	// 비어있지 않다면 형식을 체크한다. 정규식을 활용
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) { // true : 정규식 일치 / false : 정규식 불일치
				errors.rejectValue("email", "badMatch");
			}
		}
		
		// ValidationUtils객체는 자주 사용되는 값 검증 코드를 메서드 한 것
		// 위와 같이 name값이 비어있거나 공백이면 에러메세지를 담아주는 메서드가 있다.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		// 공밲까진 허용
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		// 비밀번호와 비밀번호 확인이 일치 하는가?
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualConfirmPassword()) {	// 일치하지 않는다면
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
}
