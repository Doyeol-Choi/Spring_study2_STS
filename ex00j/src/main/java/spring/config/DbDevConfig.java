package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@Profile("dev")
public class DbDevConfig {

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
}
