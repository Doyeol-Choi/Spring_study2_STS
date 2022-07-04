package spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import spring.vo.Member;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	// 생성자를 통해서 주입
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// 1. 이메일을 통한 멤버 "조회"
	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM members WHERE email = ?";
		
		// 각 레코드의 데이터를 읽어오는데 도움을 주는 클래스 : RowMapper(인터페이스) => 레코드 1차 포장
		
		// 2차 포장 list에 담는 역할
		// 1. RowMapper를 구현한 클래스를 활용한 방법
//		List<Member> list = jdbcTemplate.query(sql, new MapperSqlToMember(), email);
		
		// 2. 익명 구현 객체를 활용한 방법
		List<Member> list = jdbcTemplate.query(sql, new RowMapper<Member>() {
			
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member m = new Member(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getTimestamp("regdate"));
				
				m.setId(rs.getLong("id"));
				
				return m;
			}
			
		}, email);
		
		// 3. 람다식을 이용한 방법
//		List<Member> list = jdbcTemplate.query(sql, (rs,rowNum)->{
//			Member m = new Member(
//					rs.getString("email"),
//					rs.getString("password"),
//					rs.getString("name"),
//					rs.getTimestamp("regdate"));
//			
//			m.setId(rs.getLong("id"));
//			
//			return m;
//		}, email);
		
		return list.isEmpty()?null:list.get(0);	// 삼항 연산자
	}
	
	public List<Member> selectAll() {
		
		List<Member> list = jdbcTemplate.query(
				"SELECT * FROM members",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member m = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regdate"));
						
						m.setId(rs.getLong("id"));
						
						return m;
					}
				}); 
		
		return list;
	}
	
	public int count() {
		String sql = "SELECT count(*) FROM members";
		
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return cnt;
	}
	
	public void insertMember(Member member) {
		String sql = "INSERT INTO members VALUES (members_seq.nextval,?,?,?,?)";
		
		// ? 부분을 채우기 위한 기능을 집접 제어해야 하는 경우 => key(시퀀스) 값 가져오기
		KeyHolder keyholder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(sql, new String[] {"id"});
				
				psmt.setString(1, member.getEmail());
				psmt.setString(2, member.getPassword());
				psmt.setString(3, member.getName());
				psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
				
				return psmt;
			}
		}, keyholder);
		
		Number keyValue = keyholder.getKey();
		member.setId(keyValue.longValue());		// 시퀀스로 자동 생성된 id값 읽어오기
	}
	
	public void updateMember(Member member) {
		String sql = "UPDATE members SET name=?, password=? WHERE email=?";
		
		int num = jdbcTemplate.update(sql, member.getName(), member.getPassword(), member.getEmail());
		// num 반영된 컬럼의 개수
	}
	
	public void deleteMember(String email) {
		String sql = "DELETE FROM members WHERE email=?";
		
		int num = jdbcTemplate.update(sql, email);
		
		if(num==1) System.out.println("삭제 성공!");
	}
}
