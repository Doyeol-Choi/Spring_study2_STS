package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.dao.MemberDao;
import spring.vo.Member;

public class Main01 {

	public static void main(String[] args) {
		// 프로필 테스트용
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		ctx.getEnvironment().setActiveProfiles("dev");
		
//		ctx.load("classpath:spring-member.xml",
//				"classpath:spring-db-dev.xml",
//				"classpath:spring-db-real.xml");
		ctx.load("classpath:spring-member.xml");
		
		ctx.refresh();	// 스프링 빈 컨테이너를 재생성
		
		MemberDao dao = ctx.getBean("dao", MemberDao.class);
		
		for(Member m: dao.selectAll()) {
			System.out.println(m.getName());
		}
		
		ctx.close();

	}

}
