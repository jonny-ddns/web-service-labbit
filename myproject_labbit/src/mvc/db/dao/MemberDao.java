package mvc.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.db.DBConnectionPool;
import mvc.db.dto.MemberDto;

public class MemberDao extends DAO{
	private static MemberDao memberDao		= null;
	private List<MemberDto> memberList 		= null;
	
	private MemberDao() {
	}
	
	public static MemberDao getInstance() {
		if(memberDao == null) {
			memberDao = new MemberDao();
		}
		return memberDao;
	}
	
	//활동중인 회원 정보 가져오기
	public List<MemberDto> getMemberAll() throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - getMemberAll()");
		String sql = "SELECT * FROM tb_member WHERE isActive='y' ORDER BY joinDate DESC;";
		
		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		memberList = new ArrayList<>();
		MemberDto member = null;
		
		while(rs.next()){
			member = new MemberDto();
			member.setMemId(rs.getString("memId"))
				  .setMemName(rs.getString("memName"))
				  .setPasswd(rs.getString("passwd"))
				  .setBirthday(rs.getString("birthday"))
				  .setGender(rs.getString("gender"))
				  .setEmail(rs.getString("email"))
				  .setPhone(rs.getString("phone"))
				  .setJoinDate(rs.getDate("joinDate"))
				  .setUpdateDate(rs.getDate("updateDate"))
				  .setIsActive(rs.getString("isActive"))
				  .setIsDelete(rs.getString("isDelete"));
			memberList.add(member);
		}
		if(rs != null) { rs.close(); };
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("getMemberAll - end");
		return memberList;
	}	

	//필요한가?
	public boolean memberVerify(String memId, String passwd) throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - memberVerify()");
		String sql = "SELECT * FROM members WHERE memId=? AND passwd=?;";		
		
		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		pstmt.setString(2, passwd);
		
		rs = pstmt.executeQuery();
		
		boolean isVefied = false;
		if(rs.next() == true) {
			isVefied = true;
		}
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }

		System.out.println("memberVerify - end");
		return isVefied;
	}
	
	//회원 가입처리 메서드
	public void memberSignup(MemberDto member) throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - memberSignup()");
		String sql = "INSERT INTO tb_member("
					+ "memId, memName, passwd, birthday, gender, email, "
					+ "phone, joinDate, isActive, isDelete) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, now(), 'y', 'n');";
			
		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, member.getMemId());
		pstmt.setString(2, member.getMemName());
		pstmt.setString(3, member.getPasswd());
		pstmt.setString(4, member.getBirthday());
		pstmt.setString(5, member.getGender());
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getPhone());
		pstmt.executeUpdate();
	
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("memberSignup - end");
	}

	//회원이름으로 정보 찾기
	public MemberDto memberSearchById(String memId) throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - memberSearchByID()");
		String sql = "SELECT * FROM tb_member WHERE memId=?;";

		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		
		MemberDto member = null;
		if(rs.next()) {
			member = new MemberDto();
			member.setMemId(memId)
				  .setMemName(rs.getString("memName"))
				  .setPasswd(rs.getString("passwd"))
				  .setBirthday(rs.getString("birthday"))
				  .setGender(rs.getString("gender"))
				  .setEmail(rs.getString("email"))
				  .setPhone(rs.getString("phone"))
				  .setJoinDate(rs.getDate("joinDate"))
				  .setUpdateDate(rs.getDate("updateDate"))
				  .setIsActive(rs.getString("isActive"))
				  .setIsDelete(rs.getString("isDeleted"));
		}
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("memberSearchById - end");
		return member;
	}
	
	//비밀번호 일치여부 확인하기
	public boolean comparePasswd(MemberDto member, String passwdToCheck) throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - comparePasswd()");
		String sql = "SELECT * FROM tb_member WHERE memId = ?;";
		
		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemId());
		rs = pstmt.executeQuery();
		
		//비밀번호 일치여부 확인
		String passwd = "";
		if(rs.next()) {
			passwd = rs.getString("passwd");
		}
		boolean result = false;
		if(passwdToCheck.equals(passwd)) {
			result  = true;
		} else {
			System.out.println("비밀번호 불일치");
		}
		if(rs != null) { rs.close(); }
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("memberPwCompare - end");
		return result;
	}
	
	//회원정보 변경하기
	public void memberUpdate(MemberDto member, String memId) throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - memberUpdate()");			
		String sql = "UPDATE tb_member SET memName=?, passwd=?, birthday=?, gender=?,"
					+ "email=?, phone=?, updateDate=now(), isActive=?"
					+ "WHERE memId = ?;";
			
		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, member.getMemName());
		pstmt.setString(2, member.getPasswd());
		pstmt.setString(3, member.getBirthday());
		pstmt.setString(4, member.getGender());
		pstmt.setString(5, member.getEmail());
		pstmt.setString(6, member.getPhone());
		pstmt.setString(7, member.getIsActive());
		pstmt.setString(8, memId);		
		pstmt.executeUpdate();
		
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }

		System.out.println("memberUpdate - end");
	}
	
	//회원 삭제하기 - isActive 값을 n으로 변경
	public void memberDelete(String memId) throws SQLException, ClassNotFoundException {
		System.out.println("MemberDao - memberDelete()");
		String sql = "UPDATE tb_member SET isActive = 'n' WHERE memId = ?;";
			
		dbcp = DBConnectionPool.getInstance();
		conn = dbcp.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		pstmt.executeUpdate();
	
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
		
		System.out.println("memberDelete - end");
	}
}