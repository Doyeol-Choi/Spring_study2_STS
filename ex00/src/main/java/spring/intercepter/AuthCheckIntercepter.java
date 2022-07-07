package spring.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.vo.AuthInfo;

public class AuthCheckIntercepter extends HandlerInterceptorAdapter {

	// 로그인이 필요한 기능을 사용하기 전에 로그인 체크를 해야하기때문에 preHandle만 오버라이드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session!=null) {
			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath()+"/login");
		return false;
	}

}

//인터페이스는 추상메서드
// 인터페이스를 구현한 클래스는 반드시 추상메서드를 구현해야 함

// A라는 인터페이스를 구현한 클래스가 1000개라고 가정
// + 라는 새로운 기능이 추가 => A 인터페이스에 + 추상메서드 추가 => 1000개의 클래스에 + 메서드 구현해야함

// 1번째 해결방법 => 인터페이스의 상속
// A+라는 새로운 인터페이스를 만들고 A로부터 상속받도록 한다. 그리고 A+ 인터페이스에 + 추상메서드 추가
// +메서드가 필요없는 클래스는 A를 구현하게 만들고 +가 필요한 클래스는 A+를 구현하게 만든다.
// 그러나 이것도 기능이 추가될때마다 손이 너무 많이간다.

// 2번째 해결방법 => 디자인 패턴 : 어뎁터 패턴
// A인터페이스를 구현한 클래스를 만든다.	구현된 깡통메서드를 가진 클래스
// 실제 기능을 활용할 클래스들은 깡통클래스를 상속 받아서 사용

// 3번째 => 인터페이스를 뜯어 고침 => 디폴트 메서드 != 추상메서드 (디폴트 메서드는 추상메서드와 다르게 몸체가 있다. 하지만 디폴트 메서드를 꼭 사용하지 않아도 된다.)