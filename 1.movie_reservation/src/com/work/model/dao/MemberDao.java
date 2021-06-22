package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.work.model.dto.Member;


/**
 * ## DAO Pattern
 *  -- C 등록
 *  -- R 조회
 *  -- U 변경
 *  -- D 삭제
 *  
 * ## jdbc 프로그래밍 절차 ##
	1. jdbc driver 로딩 : 생성자에서 수행
	2. db 서버연결 : url, user, password  => Connection
	3. 연결된 서버와 통로 개설 => Statement, PreparedStatement, CallableStatement
	4. 통로이용 sql 실행 요청
	5. 실행결과 처리
	6. 자원해제
 * @author 이태규
 * @version ver.1.0
 * @since jdk1.8
 */

public class MemberDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static MemberDao instance = new MemberDao();
	
	/**
	 * Private 생성자
	 */
	private MemberDao() {
		
	}
	
	/**
	 * instance 반환 메서드
	 * @return
	 */
	public static MemberDao getInstance() {
		return instance;
	}
	
	/**
	 * <pre>
	 * PreparedStatement를 이용한 로그인 처리
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @return 회원등급, 미존재시 null
	 */
	public String SelectLogin(String Id, String pw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT GRADE FROM MEMBER_DB WHERE ID =? AND PW =? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, Id);
			stmt.setString(2, pw);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String grade = rs.getString("GRADE");
				return grade;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 로그인");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 내정보조회 / 회원상세조회
	 * </pre>
	 * @param Id 아이디
	 * @return 회원 객체
	 */
	public Member SelectGetMember(String Id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT * FROM MEMBER_DB WHERE ID =? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, Id);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				String birth = rs.getString("BIRTH");
				String mobile = rs.getString("MOBILE");
				String email = rs.getString("EMAIL");
				String entryDate = rs.getString("ENTRY_DATE");
				String grade = rs.getString("GRADE");
				int mileage = rs.getInt("MILEAGE");
				int money = rs.getInt("money");
				
				Member dto = new Member(Id, pw, name, birth, mobile, email, entryDate, grade, mileage, money);
				
				return dto;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 내정보조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 전체회원조회
	 * </pre>
	 * @return 회원객체, 미존재시 null
	 */
	public ArrayList<Member> SelectGetAllMember() {
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "Select * from Member_DB";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String Id = rs.getString("ID");
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				String birth = rs.getString("BIRTH");
				String mobile = rs.getString("MOBILE");
				String email = rs.getString("EMAIL");
				String entryDate = rs.getString("ENTRY_DATE");
				String grade = rs.getString("GRADE");
				int mileage = rs.getInt("MILEAGE");
				int money = rs.getInt("MONEY");
				
				Member dto = new Member(Id, pw, name, birth, mobile, email, entryDate, grade, mileage, money);
				
				list.add(dto);
			}
			} catch (SQLException e) {
				System.out.println("[오류] 전체회원조회");
				e.printStackTrace();
			} finally {
				factory.close(conn, stmt, rs);			
			}
			return list;		
	}
	
	/**
	 * <pre>
	 * 회원 존재 유무 조회
	 * </pre>
	 * @param Id 아이디
	 * @return 존재시 true, 미존재시 false
	 */
	public boolean SelectExistById(String Id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT ROWNUM FROM MEMBER_DB WHERE ID = ? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, Id);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int index = rs.getInt("ROWNUM");
				if (index > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("[오류] 회원 존재 유무 조회(By Id)");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 회원 존재 유무 조회
	 * </pre>
	 * @param birth 생일
	 * @param mobile 핸드폰
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 */
	public boolean SelectExistByBirthAndMobile(String birth, String mobile) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT ROWNUM FROM MEMBER_DB WHERE BIRTH = ? AND MOBILE =? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, birth);
			stmt.setString(2, mobile);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int index = rs.getInt("ROWNUM");
				if (index > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("[오류] 회원 존재 유무 조회(By Birth, Mobile)");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 회원 등록(전달 받은 객체)
	 * </pre>
	 * @param dto
	 * @return 성공시 true, 실패시 false
	 */
	public boolean InsertAddMember(Member dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		if (SelectExistById(dto.getId())) {
			try {
				conn = factory.getConnection();
				String sql = "INSERT INTO MEMBER_DB VALUES(?,?,?,?,?,?,?,'G',0,0);";
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, dto.getId());
				stmt.setString(2, dto.getPw());
				stmt.setString(3, dto.getName());
				stmt.setString(4, dto.getBirth());
				stmt.setString(5, dto.getMobile());
				stmt.setString(6, dto.getEmail());
				stmt.setString(7, dto.getEntryDate());
				stmt.setString(8, dto.getGrade());
				stmt.setInt(9, dto.getMileage());
				stmt.setInt(10, dto.getMoney());
				
				int rows = stmt.executeUpdate();
				
				if (rows > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("[오류] 회원등록");
				e.printStackTrace();
			} finally {
				factory.close(conn, stmt);
			}
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 비밀번호 변경
	 * </pre>
	 * @param memberId
	 * @param memberPw
	 * @param modifyMemberPw
	 * @return 변경된 setPw 반환, 실패시 null
	 */
	public boolean UpdateSetPw(String Id, String pw, String modifyPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
			String sql = "UPDATE MEMBER SET MEMBER_PW = ? WHERE MEMBER_ID IN "
					+ "(SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, modifyPw);
			stmt.setString(2, Id);
			stmt.setString(3, pw);
			
			int rows = stmt.executeUpdate();
			
			if (rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 비밀번호 변경");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);			
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 회원 정보 전체 변경
	 * </pre>
	 * @param dto
	 * @return
	 */
	public boolean UpdateMyInfo(Member dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
			String sql = "UPDATE MEMBER_DB SET PW = ?, NAME =?, BIRTH = ?, MOBILE = ?, EMAIL = ? WHERE ID IN "
					+ "(SELECT ID FROM MEMBER_DB WHERE ID = ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dto.getPw());
			stmt.setString(2, dto.getName());
			stmt.setString(3, dto.getBirth());
			stmt.setString(4, dto.getMobile());
			stmt.setString(5, dto.getEmail());
			stmt.setString(6, dto.getId());
			
			int rows = stmt.executeUpdate();
			
			if (rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 정보 전체 변경");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);			
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 현재 등록 인원수 조회
	 * </pre>
	 * @return 현재 등록 인원수
	 */
	public int SelectGetCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			
			String sql = "SELECT COUNT(*) FROM MEMBER_DB";
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int count = rs.getInt("COUNT(*)");
				return count;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 현재 등록 인원수 조회");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);			
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 아이디 찾기 메서드
	 * </pre>
	 * @param birth 생일
	 * @param mobile 휴대폰
	 * @return 존재시 회원 객체 반환, 미존재시 null
	 */
	
	public Member SelectSearchMyId(String birth, String mobile) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "Select * from MEMBER_DB WHERE BIRTH= ? AND MOBILE= ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, birth);
			stmt.setString(2, mobile);

			rs = stmt.executeQuery();

			if(rs.next()) {
				String Id = rs.getString("ID"); 
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				String entryDate = rs.getString("ENTRY_DATE");
				String grade = rs.getString("GRADE");
				int mileage = rs.getInt("MILEAGE");
				int money = rs.getInt("money");
					
				Member dto = new Member(Id, pw, name, birth, mobile, email, entryDate, grade, mileage, money);
				
				return dto;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 아이디 찾기");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);			
		}
		return null;
	}

	
	/**
	 * <pre>
	 * 회원 탈퇴
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @return 성공시 탈퇴회원기존정보, 실패시 null
	 */
	public boolean deleteMyInfo(String Id, String pw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
			String sql = "DELETE FROM MEMBER_DB WHERE ID= ? AND PW= ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, Id);
			stmt.setString(2, pw);

			int rows = stmt.executeUpdate();

			if(rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 아이디 찾기");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);			
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 데이터베이스에 저장된 회원 불러오기
	 * </pre>
	 * @return 저장된 회원리스트 반환
	 */
	public ArrayList<Member> SelectInitialMember() {
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT * FROM MEMBER_DB";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String Id = rs.getString("ID");
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				String birth = rs.getString("BIRTH");
				String mobile = rs.getString("MOBILE");
				String email = rs.getString("EMAIL");
				String entryDate = rs.getString("ENTRY_DATE");
				String grade = rs.getString("GRADE");
				int mileage = rs.getInt("MILEAGE");
				int money = rs.getInt("MONEY");
				
				Member dto = new Member(Id, pw, name, birth, mobile, email, entryDate, grade, mileage, money);
				
				list.add(dto);
			}
			} catch (SQLException e) {
				System.out.println("[오류] 회원 데이터베이스 불러오기");
				e.printStackTrace();
			} finally {
				factory.close(conn, stmt, rs);			
			}
			return list;		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

