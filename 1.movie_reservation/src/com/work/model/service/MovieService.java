package com.work.model.service;

import java.util.ArrayList;

import com.work.model.dao.MovieDao;
import com.work.model.dto.Member;
import com.work.model.dto.Movie;
import com.work.util.Utility;


public class MovieService {
	
	/**
	 * <pre>
	 * 영화들을 저장관리하기 위한 자료 저장구조 : Generic
	 * </pre> 
	 */
	private ArrayList<Movie> list = new ArrayList<Movie>();
	
	/**
	 * <pre>
	 * Singleton Pattern 반영 객체 인스턴스 가져오기
	 * </pre>
	 */
	private MovieDao dao = MovieDao.getInstance();
	
	/**
	 * <pre>
	 * 멤버 객체를 표현하기 위한 배열구조
	 * </pre>
	 */
	private Movie dto = new Movie();
	
	/**
	 * <pre>
	 * 기본생성자
	 * </pre>
	 */
	public MovieService() {	}
	
	/**
	 * <pre>
	 * 영화 정보를 보여주기 위한 메서드
	 * </pre>
	 * @param title 조회하고자 하는 영화의 제목
	 */
	public String getInfo(String title) {
		Movie dto = dao.SelectGetInfo(title);
		return dto.toString();
	}
	
	
	/**
	 * <pre>
	 * 영화 유무 조회
	 * </pre>
	 * @param title 영화제목
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 */
	private int exist(String title) {
		if (dao.SelectExistByTitle(title) ) {
			@SuppressWarnings("unlikely-arg-type")
			int index = list.indexOf(title);
			if (index != -1) {
				if(list.get(index).getTitle().equals(title)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 영화정보 전체 변경
	 * </pre>
	 * @param dto 변경된 정보가 담긴 객체
	 * @return 성공시 true, 실패시 false
	 */
	public void updateMovie(Movie dto) {
		if (dao.UpdateMovie(dto)) {
			System.out.println("[성공] 요청하신 정보 변경이 완료되었습니다.");
		}
		System.out.println("[오류] 입력하신 정보를 다시 확인하여 주시기 바랍니다.");
	}
	
	
	/**
	 * <pre>
	 * 현재 등록한 영화 조회
	 * </pre>
	 * @return 
	 */
	public ArrayList<Movie> getAllMovie() {
		ArrayList<Movie> list = dao.SelectGetAllMovie();
		return list;
	}
	
	/**
	 * <pre>
	 * 상영 가능 여부 확인 메서드
	 * </pre>
	 * @param dto
	 * @return
	 */
	public boolean selectMovie(Member dto) {
		int presentYear = Integer.parseInt(Utility.getCurrentDate().substring(1, 4)); 
		int memberYear = Integer.parseInt(dto.getBirth().substring(1, 4));
		
		int remainder = presentYear - memberYear;
		if (remainder > 0) {
			return true;
		}
		return false;
	}

}
