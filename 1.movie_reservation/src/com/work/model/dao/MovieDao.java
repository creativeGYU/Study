package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.Movie;

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

public class MovieDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static MovieDao instance = new MovieDao();
	
	/**
	 * Private 생성자
	 */
	private MovieDao() {
		
	}
	
	/**
	 * instance 반환 메서드
	 * @return
	 */
	public static MovieDao getInstance() {
		return instance;
	}
	
	/**
	 * <pre>
	 * 데이터베이스에 저장된 영화 불러오기
	 * </pre>
	 * @return 저장된 영화리스트 반환
	 */
	public ArrayList<Movie> SelectInitialMovie() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT * FROM MOVIE_DB";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("TITLE");
				String movieAge = rs.getString("MOVIE_AGE");
				String summary = rs.getString("SUMMARY");
				String runningTime = rs.getString("RUNNING_TIME");
				
				Movie dto = new Movie(title, movieAge, summary, runningTime);
				
				list.add(dto);
			}
			} catch (SQLException e) {
				System.out.println("[오류] 영화 데이터베이스 불러오기");
				e.printStackTrace();
			} finally {
				factory.close(conn, stmt, rs);			
			}
			return list;		
	}
	
	/**
	 * <pre>
	 * 영화 정보 불러오기 메서드
	 * </pre>
	 * @param title
	 * @return
	 */
	public Movie SelectGetInfo(String title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT * FROM MOVIE_DB WHERE TITLE = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, title);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String movieAge = rs.getString("MOVIE_AGE");
				String summary = rs.getString("SUMMARY");
				String runningTime = rs.getString("RUNNING_TIME");
				
				Movie dto = new Movie(title, movieAge, summary, runningTime);
				
				return dto;
			}
			} catch (SQLException e) {
				System.out.println("[오류] 영화 정보");
				e.printStackTrace();
			} finally {
				factory.close(conn, stmt, rs);			
			}
			return null;		
	}
	
	/**
	 * <pre>
	 * 영화 존재 유무 조회
	 * </pre>
	 * @param Id 아이디
	 * @return 존재시 true, 미존재시 false
	 */
	public boolean SelectExistByTitle(String Title) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "SELECT ROWNUM FROM MOVIE_DB WHERE TITLE = ? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, Title);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int index = rs.getInt("ROWNUM");
				if (index > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("[오류] 영화 존재 유무 조회(By Title)");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 영화 정보 전체 변경
	 * </pre>
	 * @param dto
	 * @return
	 */
	public boolean UpdateMovie(Movie dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
			String sql = "UPDATE MOVIE_DB SET TITLE = ?, MOVIE_AGE =?, SUMMARY = ?, RUNNING_TIME = ? WHERE TITLE IN "
					+ "(SELECT TITLE FROM MOVIE_DB WHERE TITLE = ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getMovieAge());
			stmt.setString(3, dto.getSummary());
			stmt.setString(4, dto.getRunningTime());
			stmt.setString(5, dto.getTitle());
			
			int rows = stmt.executeUpdate();
			
			if (rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 영화 정보 전체 변경");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);			
		}
		return false;
	}
	
	
	/**
	 * <pre>
	 * 전체 영화 조회
	 * </pre>
	 * @return 영화 객체, 미존재시 null
	 */
	public ArrayList<Movie> SelectGetAllMovie() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "Select * from MOVIE_DB";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("TITLE");
				String movieAge = rs.getString("MOVIE_AGE");
				String summary = rs.getString("SUMMARY");
				String runningTime = rs.getString("RUNNING_TIME");
				
				Movie dto = new Movie(title, movieAge, summary, runningTime);
				
				list.add(dto);
			}
			} catch (SQLException e) {
				System.out.println("[오류] 전체 영화 조회");
				e.printStackTrace();
			} finally {
				factory.close(conn, stmt, rs);			
			}
			return list;		
	}
}
