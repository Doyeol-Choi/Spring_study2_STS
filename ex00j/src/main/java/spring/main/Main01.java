package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.config.DbDevConfig;
import spring.config.DbRealConfig;
import spring.config.MemberConfig;
import spring.dao.MemberDao;
import spring.vo.Member;

public class Main01 {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		ctx.getEnvironment().setActiveProfiles("dev");
		
//		ctx.register(MemberConfig.class,DbDevConfig.class,DbRealConfig.class);
		
		ctx.register(MemberConfig.class);
		
		ctx.refresh();	// 스프링 빈 컨테이너를 재생성
		
		MemberDao dao = ctx.getBean("dao", MemberDao.class);
		
		for(Member m: dao.selectAll()) {
			System.out.println(m.getName());
		}
		
		ctx.close();

	}

}
