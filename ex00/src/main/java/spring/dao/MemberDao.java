package spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import spring.vo.ListCommand;
import spring.vo.Member;

public class MemberDao {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Member selectByEmail(String email) {
		Member m = sqlSession.selectOne("spring.member.selectByEmail", email);
		return m;
	}
	
	public List<Member> selectAll() {
		return sqlSession.selectList("spring.member.selectAll");
	}
	
	public int count() {
		return sqlSession.selectOne("spring.member.selectCount");
	}
	
	public List<Member> selectByRegDate(ListCommand searchDate) {
		return sqlSession.selectList("spring.member.selectByRegDate", searchDate);
	}
	
	public Member selectById(Long id) {
		return sqlSession.selectOne("spring.member.selectById", id);
	}
	
	public int insertMember(Member member) {
		int result = sqlSession.insert("spring.member.insertMember", member);
		sqlSession.commit();
		return result;	// 삽입, 변경, 삭제된 데이터의 개수
	}
	
	public int updateMember(Member member) {
		int result = sqlSession.update("spring.member.updateMember", member);
		sqlSession.commit();
		return result;
	}
	
	public int deleteMember(String email) {
		int result = sqlSession.delete("spring.member.deleteMember", email);
		sqlSession.commit();
		return result;
	}
	
	
	
	
	/*
	 * private JdbcTemplate jdbcTemplate;
	 * 
	 * // 생성자를 통해서 주입 public MemberDao(JdbcTemplate jdbcTemplate) {
	 * this.jdbcTemplate = jdbcTemplate; }
	 * 
	 * // 1. 이메일을 통한 멤버 "조회" public Member selectByEmail(String email) { String sql
	 * = "SELECT * FROM members WHERE email = ?";
	 * 
	 * // 각 레코드의 데이터를 읽어오는데 도움을 주는 클래스 : RowMapper(인터페이스) => 레코드 1차 포장
	 * 
	 * // 2차 포장 list에 담는 역할 // 1. RowMapper를 구현한 클래스를 활용한 방법 // List<Member> list =
	 * jdbcTemplate.query(sql, new MapperSqlToMember(), email);
	 * 
	 * // 2. 익명 구현 객체를 활용한 방법 List<Member> list = jdbcTemplate.query(sql, new
	 * RowMapper<Member>() {
	 * 
	 * @Override public Member mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { Member m = new Member( rs.getString("email"), rs.getString("password"),
	 * rs.getString("name"), rs.getTimestamp("regdate"));
	 * 
	 * m.setId(rs.getLong("id"));
	 * 
	 * return m; }
	 * 
	 * }, email);
	 * 
	 * // 3. 람다식을 이용한 방법 // List<Member> list = jdbcTemplate.query(sql,
	 * (rs,rowNum)->{ // Member m = new Member( // rs.getString("email"), //
	 * rs.getString("password"), // rs.getString("name"), //
	 * rs.getTimestamp("regdate")); // // m.setId(rs.getLong("id")); // // return m;
	 * // }, email);
	 * 
	 * return list.isEmpty()?null:list.get(0); // 삼항 연산자 }
	 * 
	 * public List<Member> selectAll() {
	 * 
	 * List<Member> list = jdbcTemplate.query( "SELECT * FROM members", new
	 * RowMapper<Member>() {
	 * 
	 * @Override public Member mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { Member m = new Member( rs.getString("email"), rs.getString("password"),
	 * rs.getString("name"), rs.getTimestamp("regdate"));
	 * 
	 * m.setId(rs.getLong("id"));
	 * 
	 * return m; } });
	 * 
	 * return list; }
	 * 
	 * public int count() { String sql = "SELECT count(*) FROM members";
	 * 
	 * Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);
	 * 
	 * return cnt; }
	 * 
	 * public void insertMember(Member member) { String sql =
	 * "INSERT INTO members VALUES (members_seq.nextval,?,?,?,?)";
	 * 
	 * // ? 부분을 채우기 위한 기능을 집접 제어해야 하는 경우 => key(시퀀스) 값 가져오기 KeyHolder keyholder =
	 * new GeneratedKeyHolder();
	 * 
	 * jdbcTemplate.update(new PreparedStatementCreator() {
	 * 
	 * @Override public PreparedStatement createPreparedStatement(Connection con)
	 * throws SQLException { PreparedStatement psmt = con.prepareStatement(sql, new
	 * String[] {"id"});
	 * 
	 * psmt.setString(1, member.getEmail()); psmt.setString(2,
	 * member.getPassword()); psmt.setString(3, member.getName());
	 * psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
	 * 
	 * return psmt; } }, keyholder);
	 * 
	 * Number keyValue = keyholder.getKey(); member.setId(keyValue.longValue()); //
	 * 시퀀스로 자동 생성된 id값 읽어오기 }
	 * 
	 * public void updateMember(Member member) { String sql =
	 * "UPDATE members SET name=?, password=? WHERE email=?";
	 * 
	 * int num = jdbcTemplate.update(sql, member.getName(), member.getPassword(),
	 * member.getEmail()); // num 반영된 컬럼의 개수 }
	 * 
	 * public void deleteMember(String email) { String sql =
	 * "DELETE FROM members WHERE email=?";
	 * 
	 * int num = jdbcTemplate.update(sql, email);
	 * 
	 * if(num==1) System.out.println("삭제 성공!"); }
	 * 
	 * // 날짜를 이용한 회원 조회 메서드 public List<Member> selectByRegDate(Date from, Date to)
	 * { String sql =
	 * "SELECT * FROM members WHERE regDate BETWEEN ? AND ? ORDER BY regDate";
	 * 
	 * List<Member> list = jdbcTemplate.query(sql, new RowMapper<Member>() {
	 * 
	 * @Override public Member mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { Member m = new Member( rs.getString("email"), rs.getString("password"),
	 * rs.getString("name"), rs.getTimestamp("regDate")); m.setId(rs.getLong("id"));
	 * return m; }}, from, to); return list; }
	 * 
	 * // ID 번호를 통해 회원 조회를 하기 위한 메서드 public Member selectById(Long id) { String sql
	 * = "SELECT * FROM members WHERE id=?";
	 * 
	 * List<Member> result = jdbcTemplate.query(sql, new RowMapper<Member>() {
	 * 
	 * @Override public Member mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { Member m = new Member( rs.getString("email"), rs.getString("password"),
	 * rs.getString("name"), rs.getTimestamp("regDate")); m.setId(rs.getLong("id"));
	 * return m; }}, id);
	 * 
	 * return result.isEmpty()?null:result.get(0); }
	 */
	
}
