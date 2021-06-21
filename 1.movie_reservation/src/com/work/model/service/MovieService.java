package com.work.model.service;

import java.util.ArrayList;

import com.sun.corba.se.impl.transport.DefaultIORToSocketInfoImpl;
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
	 * 기본생성자 : 초기화 영화 등록 수행
	 * </pre>
	 */
	public MovieService() {
		int count = initMovie();
		System.out.println("초기화 영화 등록작업이 완료되었습니다. : " + count);
	}
	
	/**
	 * <pre>
	 * 영화 정보를 보여주기 위한 메서드
	 * </pre>
	 * @param index 조회하고자 하는 영화의 위치
	 */
	public void getInfo(int index) {
		System.out.println(list.get(index));
	}
	
	
	/**
	 * <pre>
	 * 테스트를 위한 영화 초기화 등록 메서드
	 * </pre>
	 * @return 초기화 영화등록 개수
	 */
	private int initMovie() {
		Movie dto1 = new Movie("도라에몽 : 스탠바이미 2", "00", "“할머니를 만나고 싶어!” 어느 날 진구는...", "00");
		Movie dto2 = new Movie("분노의 질주: 더 얼티메이트", "12", "도미닉(빈 디젤)은 자신과 가장 가까웠던 형제...", "00");
		Movie dto3 = new Movie("컨저링 3: 악마가 시켰다", "15", "1981년, 미국 역사상 최초로 잔혹한...", "00");
		Movie dto4 = new Movie("캐시트럭", "19", "캐시트럭을 노리는 무장 강도에 의해 ...", "00");
		Movie dto5 = new Movie("크루엘라", "12", "처음부터 난 알았어. 내가 특별하단 걸...", "00");
		
		addMovie(dto1);
		addMovie(dto2);
		addMovie(dto3);
		addMovie(dto4);
		addMovie(dto5);
		
		return list.size();
	}
	
	/**
	 * <pre>
	 * 영화 등록 메서드
	 * </pre>
	 * @param dto 등록할 영화정보가 담겨있는 객체
	 */
	private void addMovie(Movie dto) {
		int index = exist(dto.getTitle());
		if (index >= 0) {
			System.out.println("[오류] 동일한 영화제목" + dto.getTitle() + "가 존재합니다.");
		} 
		list.add(dto);
	}
	
	/**
	 * <pre>
	 * 영화 유무 조회
	 * </pre>
	 * @param title 영화제목
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 */
	private int exist(String title) {
		for (int index = 0; index < list.size(); index++) {
			if(list.get(index).getTitle().equals(title)) {
				return index;
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
	public boolean updateMovie(Movie dto) {
		int index = exist(dto.getTitle());
		if (index >= 0) {
			list.set(index, dto);
			return true;
		}
		return false;
	}
	
	
	/**
	 * <pre>
	 * 현재 등록한 영화 조회
	 * </pre>
	 * @return 
	 */
	public ArrayList<Movie> getAllMovie() {
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
