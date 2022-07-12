package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
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

	@Autowired
	private DataSource dataSource;
	
	// 트랜젝션 관리 객체
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
		txMgr.setDataSource(dataSource);
		return txMgr;
	}
	
	// DB 연결을 위해서 연결객체를 생성하기 위한 커넥션풀(DataSource)
//	@Bean
//	public DataSource dataSource() {
//		ComboPooledDataSource ds = new ComboPooledDataSource();
//		
//		try {
//			ds.setDriverClass("oracle.jdbc.OracleDriver");			
//		} catch(PropertyVetoException e) {
//			throw new RuntimeException(e);
//		}
//		ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		ds.setUser("green");
//		ds.setPassword("1234");
//		ds.setMaxPoolSize(20);
//		
//		return ds;
//	}
	
	// jdbcTemplate
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
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
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		configurer.setLocation(new ClassPathResource("message/db.properties"));
		
		return configurer;
	}
	
	@Configuration
	@Profile("dev")
	public static class DataSourceDev {
		
		@Value("${db.driver}")
		private String driver;
		@Value("${db.dev.jdbcUrl}")
		private String jdbcUrl;
		@Value("${db.dev.user}")
		private String user;
		@Value("${db.dev.password}")
		private String password;
		
		@Bean
		public DataSource dataSource() {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			
			try {
				ds.setDriverClass(driver);			
			} catch(PropertyVetoException e) {
				throw new RuntimeException(e);
			}
			ds.setJdbcUrl(jdbcUrl);
			ds.setUser(user);
			ds.setPassword(password);
			ds.setMaxPoolSize(20);
			
			return ds;
		}
	}
	
	@Configuration
	@Profile("real")
	public static class DataSourceReal {
		
		@Value("${db.driver}")
		private String driver;
		@Value("${db.real.jdbcUrl}")
		private String jdbcUrl;
		@Value("${db.real.user}")
		private String user;
		@Value("${db.real.password}")
		private String password;
		
		@Bean
		public DataSource dataSource() {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			
			try {
				ds.setDriverClass(driver);			
			} catch(PropertyVetoException e) {
				throw new RuntimeException(e);
			}
			ds.setJdbcUrl(jdbcUrl);
			ds.setUser(user);
			ds.setPassword(password);
			ds.setMaxPoolSize(20);
			
			return ds;
		}
	}
}
