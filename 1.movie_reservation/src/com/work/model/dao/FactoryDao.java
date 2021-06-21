package com.work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * 모든 DAO 클래스에서 사용하기 위한 
 * -- Connection 반환 
 * -- close() 자원해제를 담당하는 기능으로만 분리설계
 * 
 * -- 모든 DAO 클래스에서 getConnection(), close(conn, stmt, rs) : 호출사용
 * 
 * -- Singleton Pattern 반영
 * 1. private 생성자
 * 2. private static 클래스이름 instance = new 클래스이름();
 * 3. public static 클래스이름 getInstance() {
 * 		return instance;
 * 	  }
 * </pre>
 * @author 이태규
 * @version 
 * @since 
 *
 */

public class FactoryDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String user = "scott";
	private String password = "tiger";
	
	private static FactoryDao instance = new FactoryDao();
	
	/**
	 * Singleton Pattern 반영하기 위한 생성자
	 */
	private FactoryDao() {
		try {
			Class.forName(driver);
			System.out.println("[성공] 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("[오류] 드라이버 로딩 오류");
			e.printStackTrace();
		}
		
	}
	
	public static FactoryDao getInstance() {
		return instance;
	}
	
	/**
	 * DB연결 Connection 반환하는 메서드
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("[성공] FactoryDao DB 연결 성공");
		} catch (SQLException e) {
			System.out.println("[오류] FactoryDao DB 연결 실패");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * <pre>
	 * 자원해제 메서드 : SELECT 수행에 대한 자원
	 * </pre>
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn, Statement stmt, ResultSet rs) {
			try {
				if (rs != null) {
					rs.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * <pre>
	 * 자원해제 : CUD 수행에 대한 자원
	 * </pre>
	 * @param conn
	 * @param stmt
	 */
	public void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
