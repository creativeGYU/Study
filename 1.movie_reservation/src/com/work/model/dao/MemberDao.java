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
	public String login(String Id, String pw) {
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
			System.out.println("[오류] 로그인 실패");
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
	public Member getMember(String Id) {
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
			System.out.println("[오류] 내정보조회 실패");
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
	public ArrayList<Member> getAllMember() {
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "Select * from Member";
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
				System.out.println("[오류] 전체회원조회 실패");
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
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 */
	public int existById(String Id) {
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
				int index = rs.getInt("ROWNUM")-1 ;
				return index;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 아이디를 다시 확인해주세요.");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 회원 존재 유무 조회
	 * </pre>
	 * @param birth 생일
	 * @param mobile 핸드폰
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 */
	public int existByBirthAndMobile(String birth, String mobile) {
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
				int index = rs.getInt("ROWNUM")-1 ;
				return index;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 입력하신 정보를 다시 확인해주세요.");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
