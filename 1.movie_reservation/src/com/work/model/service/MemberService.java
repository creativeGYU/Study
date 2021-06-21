package com.work.model.service;

import java.util.ArrayList;

import com.work.model.dao.FactoryDao;
import com.work.model.dao.MemberDao;
import com.work.model.dto.Member;
import com.work.util.Utility;

/**
 * <pre>
 * Collection API 활용 변경
 * -- ArrayList
 * -- Generic collection 변경
 * </pre>
 * @author COOLGYU
 * @version ver.1.0
 * @since jdk1.8
 */
public class MemberService {
	
	/**
	 * <pre>
	 * 회원들을 저장관리하기 위한 자료 저장구조
	 * </pre> 
	 */
	private ArrayList<Member> list = new ArrayList<Member>();
	
	/**
	 * <pre>
	 * Singleton Pattern 반영 객체 인스턴스 가져오기
	 * </pre>
	 */
	private MemberDao dao = MemberDao.getInstance();
	
	/**
	 * <pre>
	 * 기본생성자 : 초기화 회원 등록 수행
	 * </pre>
	 */
	public MemberService() {
		int count = initMember();
		System.out.println("초기화 회원 등록작업이 완료되었습니다. : " + count);
	}
	
	/**
	 * <pre>
	 * 로그인
	 * </pre>
	 * @param Id
	 * @param Pw
	 * @return 성공시 회원 등급, 실패시 null
	 */
	public String login(String Id, String pw) {
		String grade = dao.login(Id,pw);
		if (grade != null) {
			return grade;
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 회원 상세조회
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @return 존재하면 회원객체 반환, 미존재 null
	 */
	public Member getMember(String Id) {
		return dao.getMember(Id);
	}
	
	/**
	 * <pre>
	 * 현재 등록한 전체 회원 조회
	 * </pre>
	 * @return 
	 */
	public ArrayList<Member> getAllMember() {
		return dao.getAllMember();
	}	
//		for (int index = 0; index < list.size(); index++) {
//			switch(list.get(index).getGrade()) {
//				case "A" :
//					Utility.printLine("관리자 회원");
//					System.out.println((Member)list.get(index));
//					break;
//					
//				case "VVIP" : 
//					Utility.printLine("VVIP 회원");
//					System.out.println((Member)list.get(index));
//					break;
//					
//				case "VIP" : 
//					Utility.printLine("VIP  회원");
//					System.out.println((Member)list.get(index));
//					break;
//					
//				case "G" :
//					Utility.printLine("일반  회원");
//					System.out.println((Member)list.get(index));
//					break;
//					
//				default :
//					break;
//			}
//		}
	
	/**
	 * <pre>
	 * 회원 존재 유무 조회
	 * </pre>
	 * @param Id 아이디
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 * @throws RecordNotFoundException 
	 */
	public int existById(String Id) {
		int index = dao.existById(Id);
		if (index != -1) {
			return index;
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 회원 존재 유무 조회
	 * </pre>
	 * @param birth 생일
	 * @param mobile 휴대폰
	 * @return 존재시 저장위치 인덱스 번호, 미존재시 -1
	 */
	public int existByBirthAndMobile(String birth, String mobile) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getBirth().equals(birth)) {
				Member dto = list.get(index);
				if (dto.getMobile().equals(mobile)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 테스트를 위한 회원 초기화 등록 메서드
	 * </pre>
	 * @return 초기화 회원등록 인원수
	 */
	public int initMember() {
		Member dto1 = new Member("user01", "password01", "황윤아", "2011-06-04", "010-1234-1000", "user01@work.com1", "2021-06-04", "G", 5000 , 6000 );
		Member dto2 = new Member("user02", "password02", "이택근", "2005-06-04", "010-1234-1000", "user02@work.com1", "2021-06-04", "VIP", 25000 , 4000);
		Member dto3 = new Member("user03", "password03", "유동국", "1990-06-04", "010-1234-1000", "user03@work.com1", "2021-06-04", "VVIP", 42000 , 10000);
		Member dto4 = new Member("user04", "password04", "이태규", "1986-06-04", "010-1234-4000", "user04@work.com1", "2021-06-04", "VIP", 35000 , 10000);
		Member dto5 = new Member("administrator", "admin1004", "김혜진", "1996-06-04", "010-1234-5000", "anmin@work.com1", "2021-06-04", "A", 10000 , 10000);
		
		addMember(dto1);
		addMember(dto2);
		addMember(dto3);
		addMember(dto4);
		addMember(dto5);
		
		return list.size();
	}
	
	/**
	 * <pre>
	 * 회원 등록(전달 받은 객체)
	 * </pre>
	 * @param dto 회원 가입 회원정보
	 */
	private void addMember(Member dto) {
		int index = existById(dto.getId());
		if (index >= 0) {
			System.out.println("[오류] 동일한 아이디" + dto.getId() + "가 존재합니다.");
		}
		list.add(dto);
	}
	
	/**
	 * <pre>
	 * 회원등록
	 * - 사용자 입력 데이터 : 아이디, 비밀번호, 이름, 생일, 휴대폰
	 * - 시스템 최초 가입시 일반회원 가입처리 : 가입일, 등급, 마일리지, 소지금
	 * </pre>
	 * @param Id
	 * @param pw
	 * @param name
	 * @param birth
	 * @param mobile
	 * @param email
	 */
	public void addMember(String Id, String pw, String name, String birth, String mobile, String email) {
		Member dto = new Member(Id, pw, name, birth, mobile, email);
		dto.setGrade("G");
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setMileage(0);
		dto.setMoney(0);
		
		addMember(dto);
	}
	
	/**
	 * <pre>
	 * 회원 탈퇴
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @return 성공시 탈퇴회원기존정보, 실패시 null
	 */
	public Member deleteMyInfo(String Id, String pw) {
		int index = existById(Id);
		if(index >= 0 && list.get(index).getPw().equals(pw)) {
			return (Member)list.remove(index);
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 회원정보 전체 변경
	 * </pre>
	 * @param dto 변경된 정보가 담긴 객체
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateMyInfo(Member dto) {
		int index = existById(dto.getId());
		if (index >= 0 ) {
			list.set(index, dto);
			return true;
		}
		return false;
	}
	
	

	
	/**
	 * 현재 등록 인원수 조회
	 * @return 현재 등록 인원수
	 */
	public int getCount() {
		return list.size();
	}
	
	/**
	 * <pre>
	 * 비밀번호 변경
	 * -- 아규먼트 : 아이디, 비밀번호, 변경비밀번호
	 * </pre> 
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @param modifyPw 변경 비밀번호
	 * @return 존재 및 일치시 true, 미존재 or 미일치시 false 
	 */
	public boolean setPw(String Id, String pw, String modifyPw) {
		int index = existById(Id);
		if (index >= 0 && list.get(index).getPw().equals(pw)) {
			list.get(index).setPw(modifyPw);
			return true;
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 아이디 찾기 메서드
	 * </pre>
	 * @param birth 생일
	 * @param mobile 휴대폰
	 * @return 존재시 회원 객체 반환, 미존재시 null
	 */
	public Member searchMyId(String birth, String mobile) {
		int index = existByBirthAndMobile(birth, mobile);
		if (index >= 0 && list.get(index).getBirth().equals(birth)) {
			return (Member)list.get(index);
		}
		return null;
	}
	
//	/**
//	 * <pre>
//	 * 예매내역조회
//	 * </pre>
//	 * @param reservationNumber 예매번호
//	 * @return 
//	 */
//	public String searchTicket(String ticketNumber) {
//		Reservation reservation = new Reservation();
//		if(reservation.getTicketNumber().equals(ticketNumber)) {
//			
//		}
//		return null;
//	}
	
	
}
