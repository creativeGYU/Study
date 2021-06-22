package com.work.model.service;

import java.util.ArrayList;

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
	 * 멤버 객체를 표현하기 위한 배열구조
	 * </pre>
	 */
	private Member dto = new Member();
	
	/**
	 * <pre>
	 * 기본생성자 
	 * </pre>
	 */
	public MemberService() {
//		int count = initMember();
//		System.out.println("초기화 회원 등록작업이 완료되었습니다. : " + count);
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
		String grade = dao.SelectLogin(Id,pw);
		if (grade != null) {
			return "(" + grade + ")" + "님 환영합니다!";
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
		return dao.SelectGetMember(Id);
	}
	
	/**
	 * <pre>
	 * 현재 등록한 전체 회원 조회
	 * </pre>
	 * @return 
	 */
	public ArrayList<Member> getAllMember() {
		ArrayList<Member> list = dao.SelectGetAllMember();
		return list;
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
	 */
	public int existById(String Id) {
		if (dao.SelectExistById(Id)) {
			@SuppressWarnings("unlikely-arg-type")
			int index = list.indexOf(Id);
			if (index != -1) {
				if(list.get(index).getId().equals(Id)) {
					return index;
				}
			}
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
		if (dao.SelectExistByBirthAndMobile(birth, mobile)) {
			@SuppressWarnings("unlikely-arg-type")
			int index = list.indexOf(birth);
			if (index != -1) {
				if (list.get(index).getBirth().equals(birth)) {
					Member dto = list.get(index);
					if (dto.getMobile().equals(mobile)) {
						return index;
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 회원 등록(전달 받은 객체)
	 * </pre>
	 * @param dto 회원 가입 회원정보
	 */
	private void addMember(Member dto) {
		if (dao.InsertAddMember(dto)) {
			System.out.println("[성공] 회원등록이 완료되었습니다. " + dto.getId() + "님의 가입을 축하드립니다!");
		}
		System.out.println("[오류] 입력하신 정보를 다시 확인하여 주시기 바랍니다.");
	}
	
	/**
	 * <pre>
	 * 비밀번호 변경
	 * -- 아규먼트 : 아이디, 비밀번호, 변경비밀번호
	 * </pre> 
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @param modifyPw 변경 비밀번호
	 */
	public void setPw(String Id, String pw, String modifyPw) {
		if (dao.UpdateSetPw(Id, pw, modifyPw)) {
			System.out.println("[성공] 요청하신 정보 변경이 완료되었습니다.");
		}
		System.out.println("[오류] 입력하신 정보를 다시 확인하여 주시기 바랍니다.");
	}
	
	/**
	 * <pre>
	 * 회원정보 전체 변경
	 * </pre>
	 * @param dto 변경된 정보가 담긴 객체
	 * @return 성공시 true, 실패시 false
	 */
	public void updateMyInfo(Member dto) {
		if (dao.UpdateMyInfo(dto)) {
			System.out.println("[성공] 요청하신 정보 변경이 완료되었습니다.");
		}
		System.out.println("[오류] 입력하신 정보를 다시 확인하여 주시기 바랍니다.");
	}
	
	/**
	 * <pre>
	 * 현재 등록 인원수 조회
	 * </pre>
	 * @return 현재 등록 인원수
	 */
	public int getCount() {
		return dao.SelectGetCount();
	}
	
	/**
	 * <pre>
	 * 아이디 찾기 메서드
	 * </pre>
	 * @param birth 생일
	 * @param mobile 휴대폰
	 * @return 존재시 ID 반환, 미존재시 null
	 */
	public Member searchMyId(String birth, String mobile) {
		return dao.SelectSearchMyId(birth, mobile);
	}
	
	/**
	 * <pre>
	 * 회원 탈퇴
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @return 성공시 성공메세지 출력, 실패시 실패메세지 출력
	 */
	public void deleteMyInfo(String Id, String pw) {
		if (dao.deleteMyInfo(Id, pw)) {
			System.out.println("[성공] " + Id + "님 그동안 이용해주셔서 감사합니다.");
		}
		System.out.println("[실패] 입력하신 정보를 다시 확인해주세요.");
	}
	
	/**
	 * <pre>
	 * 회원등록
	 * - 사용자 입력 데이터 : 아이디, 비밀번호, 이름, 생일, 휴대폰
	 * - 시스템 최초 가입시 일반회원 가입처리 : 가입일, 등급, 마일리지, 소지금
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @param name 이름
	 * @param birth 생일
	 * @param mobile 핸드폰
	 * @param email 이메일
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
	 * 데이터베이스에 저장된 회원 목록 불러오기 메서드
	 * </pre>
	 * @return 현재 회원 인원수
	 */
	public int initialMember() {
		list = dao.SelectInitialMember();
		return list.size();
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
