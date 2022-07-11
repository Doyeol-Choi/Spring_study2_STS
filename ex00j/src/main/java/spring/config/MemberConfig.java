package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import spring.dao.MemberDao;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class MemberConfig {

	// 트랜젝션 관리 객체
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
		txMgr.setDataSource(dataSource());
		return txMgr;
	}
	
	// DB 연결을 위해서 연결객체를 생성하기 위한 커넥션풀(DataSource)
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		try {
			ds.setDriverClass("oracle.jdbc.OracleDriver");			
		} catch(PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUser("green");
		ds.setPassword("1234");
		ds.setMaxPoolSize(20);
		
		return ds;
	}
	
	// jdbcTemplate
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	// DAO
	@Bean
	public MemberDao dao() {
		return new MemberDao(jdbcTemplate());
	}
	
	@Bean
	public MemberRegisterService regSvc() {
		return new MemberRegisterService(dao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService(dao());
	}
	
	@Bean
	public AuthService authService() {
		// 생성자 주입과 setter주입의 차이 확인
		AuthService as = new AuthService();
		as.setMemberDao(dao());
		return as;
	}
}
