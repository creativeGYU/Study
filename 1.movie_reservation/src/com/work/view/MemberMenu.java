package com.work.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.work.model.dto.Member;
import com.work.model.service.MemberService;
import com.work.model.service.MovieService;
import com.work.model.service.SeatDoraemong;
import com.work.util.Utility;

/**
 * <pre>
 * 회원관리시스템 메뉴 구성
 * </pre>
 *  
 * @author 이태규
 * @version ver.1.0
 * @since jdk1.8
 */
public class MemberMenu {
	
	/**
	 * <pre>
	 * 회원 관리 서비스 객체 생성
	 * </pre>
	 */
	public static MemberService service = new MemberService();

	/**
	 * <pre>
	 * 영화 관리 서비스 객체 생성
	 * </pre>
	 */
	public static MovieService movie = new MovieService();
	
	/**
	 * <pre>
	 * 회원 관리 메인 메뉴 메서드
	 * </pre>
	 */
	public void mainMenu() {
		Utility.printLine("영화 예매 프로그램");
		
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디 찾기");
		System.out.println("4. 비밀번호 찾기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNumber = inputNumber();
		
		switch(menuNumber) {
			case 1 :
				loginMenu();
				break;
			case 2 :
				addMemberMenu();
				break;
			case 3 :
				getIdMenu();
				break;
			case 4 :
				getPwMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
		
	}
	public void getPwMenu() {
		
	}
	public void getIdMenu() {
		
	}
	public void addMemberMenu() {
		Utility.printLine("아래 사항을 기재하여 주시기 바랍니다.");
		
		System.out.print("아이디(Id) : ");
		String Id = inputString();
		
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		
		System.out.print("이름(Name) : ");
		String name = inputString();
		
		System.out.print("생일(Birth) : ex)2011-05-02");
		System.out.println();
		String birth = inputString();
		
		System.out.print("휴대폰(Mobile) : ex)010-1234-5678");
		System.out.println();
		String mobile = inputString();
		
		System.out.print("이메일(Email) : ex)user01@naver.com");
		System.out.println();
		String email = inputString();
		
		new Member(Id, pw, name, birth, mobile, email);
		service.addMember(Id, pw, name, birth, mobile, email);
		System.out.println("회원가입이 완료되었습니다. 짝짝짝");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		mainMenu();
	}
	
	/**
	 * <pre>
	 * 로그인 메뉴
	 * </pre>
	 */
	public void loginMenu() {
		Utility.printLine("로그인해주시기 바랍니다.");
		System.out.print("아이디(Id) : ");
		String Id = inputString();
		
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		
		Member dto = service.getMember(Id);
		String welcome = service.login(Id, pw);
		System.out.println(welcome);
		
		switch(dto.getGrade()) {
			case "G" :
			case "VIP" :
			case "VVIP" :
				serviceMainMenu();
				break;
			case "A" :
				adminMainMenu();
				break;
			default : 
				System.out.println("[오류] 입력하신 정보를 다시 확인하여 주시기 바랍니다.");
				mainMenu();
		}
	}
	
	/**
	 * <pre>
	 * 관리자 전용 서비스 메뉴
	 * </pre>
	 */
	private void adminMainMenu() {
		Utility.printLine("관리자 전용 서비스 메뉴");
		System.out.println("1. 영화변경");
		System.out.println("2. 회원조회");
		System.out.println("3. 내정보조회");
		System.out.println("4. 내정보변경");
		System.out.println("5. 로그아웃");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				movieMainMenu();
				break;
			case 2 :
				searchMemberMenu();
				break;
			case 3 :
				searchMyInfoMenu2();
				break;
			case 4 :
				updateMyInfoMenu2();
				break;
			case 5 :
				Utility.printLine("[로그아웃] 안녕히 가세요. ^ㅡㅡㅡ^");
				mainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
	}
	
	/**
	 * <pre>
	 * 영화를 관리하기 위한 메뉴(관리자 전용)
	 * </pre>
	 */
	private void movieMainMenu() {
		
		Utility.printLine("영화 변경");
		
		System.out.println(movie.toString());
		System.out.println("2. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("변경할 영화 번호를 입력해주세요. : ");
		System.out.println();
	}
	
	/**
	 * <pre>
	 * 회원들을 조회하기 위한 메뉴(관리자 전용)
	 * </pre>
	 */
	private void searchMemberMenu() {
		Utility.printLine("회원 조회 서비스");
		System.out.println("1. 전체회원조회");
		System.out.println("2. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				searchMemberMenu2();
				break;
			case 2 :
				adminMainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
	}
	
	private void searchMemberMenu2() {
		Utility.printLine("전체 회원 조회 서비스");
		service.getAllMember();
		
		System.out.println("조회가 완료되었습니다.");
		
		System.out.println("1. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				searchMemberMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
	}
	
	/**
	 * <pre>
	 * 내정보를 변경 및 등록하기 위한 메뉴(관리자 전용)
	 * </pre>
	 */
	private void updateMyInfoMenu2() {
		Utility.printLine("변경되는 사항을 입력해주세요.");
		
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		
		System.out.print("이름(Name) : ");
		String name = inputString();
		
		System.out.print("생일(Birth) : ex)2011-05-02");
		String birth = inputString();
		
		System.out.print("휴대폰(Mobile) : ex)010-1234-5678");
		String mobile = inputString();
		
		Member dto = service.searchMyId(birth, mobile);
		service.updateMyInfo(dto);
		
		System.out.println("변경 및 등록이 완료되었습니다.");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.println("1. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				serviceMainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
		
	}
	
	/**
	 * <pre>
	 * 내정보를 조회하기 위한 메뉴(관리자 전용)
	 * </pre>
	 */
	private void searchMyInfoMenu2() {
		Utility.printLine("확인을 위해 정보를 입력해주세요.");
		System.out.print("아이디(Id) : ");
		String Id = inputString();
		
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		
		Member dto = service.getMember(Id);
		String welcome = service.login(Id, pw);
		System.out.println(welcome);
		System.out.println(dto);
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.println("1. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				adminMainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
		
	}
	
	/**
	 * <pre>
	 * 관리자를 제외한 회원 전용 서비스 메인메뉴
	 * </pre>
	 */
	private void serviceMainMenu() {
//		Member dto = new Member();
//		Utility.printLine(dto.getId() + "님 환영합니다.");
//		System.out.println("[마일리지 : " + dto.getMileage() + ", 소지금 : " + dto.getMoney() + 
//		", 등급 : " + dto.getGrade() + "]");
		Utility.printLine("회원 전용 서비스 메뉴");
		System.out.println("1. 예매하기");
		System.out.println("2. 내정보조회");
		System.out.println("3. 내정보변경");
		System.out.println("4. 탈퇴하기");
		System.out.println("5. 예매내역조회");
		System.out.println("6. 로그아웃");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				ticketingMenu();
				break;
			case 2 :
				searchMyInfoMenu();
				break;
			case 3 :
				updateMyInfoMenu();
				break;
			case 4 :
				deleteMyInfoMenu();
				break;
			case 5 :
				break;
			case 6 :
				Utility.printLine("[로그아웃] 안녕히 가세요. ^ㅡㅡㅡ^");
				mainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
	}
	
	/**
	 * <pre>
	 * 탈퇴하기 위한 메뉴
	 * </pre>
	 */
	private void deleteMyInfoMenu() {
		Utility.printLine("확인을 위해 정보를 입력해주세요.");
		System.out.print("아이디(Id) : ");
		String Id = inputString();
		
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		service.deleteMyInfo(Id, pw);
		System.out.println("1. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				mainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
	}
	
	/**
	 * <pre>
	 * 영화를 예매하기 위한 메뉴
	 * </pre>
	 */
	private void ticketingMenu() {
		Utility.printLine("예매하기");
		System.out.println("1. 영화 선택하기");
		System.out.println("2. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				chooseMovie();
				break;
			case 2 :
				serviceMainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
	}
	
	
	private void chooseMovie() {
		Utility.printLine("영화를 선택해주세요.");
		System.out.println("1. 도라에몽 : 스탠바이미 2");
		System.out.println("2. 분노의 질주: 더 얼티메이트");
		System.out.println("3. 컨저링 3: 악마가 시켰다");
		System.out.println("4. 캐시트럭");
		System.out.println("5. 크루엘라");
		System.out.println("6. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1 :
			Doraemong();
			break;
		case 2 :
			ticketingMenu();
			break;
		case 3 :
			ticketingMenu();
			break;
		case 4 :
			ticketingMenu();
			break;
		case 5 :
			ticketingMenu();
			break;	
		case 6 :
			ticketingMenu();
			break;
		case 9 :
			exitMenu();
			break;
		default :
			System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
			break;
		}
	}
	
	/**
	 * 뮤지컬 더 리퍼 정보 및 예약
	 */
	public void Doraemong() {
		int menu = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		SeatDoraemong sd = new SeatDoraemong();
		
		while(true) {
			Utility.printLine("도라에몽 : 스탠바이미 2");
			System.out.println("1. 영화정보 확인하기");
			System.out.println("2. 예약하기");
			System.out.println("3. 예약 내역 조회하기");
			System.out.println("4. 예약 취소하기");
			System.out.println("5. 뒤로 가기");
			System.out.println("9. 프로그램 종료");
			Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
			System.out.print("메뉴 번호를 입력해주세요. : ");
			System.out.println();
			
			menu = Integer.parseInt(scanner.nextLine());
			
			switch(menu) {
			case 1:
				movie.getInfo("도라에몽 : 스탠바이미 2");
				break;
			case 2:
				sd.reservation();
				break;
			case 3:
				sd.showReserveSeat();
				break;
			case 4:
				sd.delReserve();
				break;
			case 5:
				chooseMovie();
				break;					
			case 9:
				System.out.println("시스템을 종료합니다.");
				exitMenu();
				break;
				default:
					System.out.println("없는 메뉴입니다. 다시 입력해주세요.");
					continue;
				
			}
		}
	}
	/**
	 * <pre>
	 * 내정보를 조회하기 위한 메뉴
	 * </pre>
	 */
	private void searchMyInfoMenu() {
		Utility.printLine("확인을 위해 정보를 입력해주세요.");
		System.out.print("아이디(Id) : ");
		String Id = inputString();
		
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		
		Member dto = service.getMember(Id);
		String welcome = service.login(Id, pw);
		System.out.println(welcome);
		System.out.println(dto);
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.println("1. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				serviceMainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
		
	}
	
	/**
	 * <pre>
	 * 내정보를 변경 및 등록하기 위한 메뉴
	 * </pre>
	 */
	private void updateMyInfoMenu() {
		Utility.printLine("변경되는 사항을 입력해주세요.");
		System.out.print("비밀번호(Pw) : ");
		String pw = inputString();
		
		System.out.print("이름(Name) : ");
		String name = inputString();
		
		System.out.print("생일(Birth) : ex)2011-05-02");
		String birth = inputString();
		
		System.out.print("휴대폰(Mobile) : ex)010-1234-5678");
		String mobile = inputString();
		
		Member dto = service.searchMyId(birth, mobile);
		service.updateMyInfo(dto);
		
		System.out.println("변경 및 등록이 완료되었습니다.");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.println("1. 뒤로 가기");
		System.out.println("9. 프로그램 종료");
		Utility.printLine("환영합니다 ^ㅡㅡㅡ^          ");
		System.out.print("메뉴 번호를 입력해주세요. : ");
		System.out.println();
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
			case 1 :
				serviceMainMenu();
				break;
			case 9 :
				exitMenu();
				break;
			default :
				System.out.println("[오류] 없는 번호입니다. 다시 확인해주세요.");
				break;
		}
		
	}
	
	/**
	 * 회원정보 파일 가져와서 회원자료저장구조 메모리에 저장하기
	 * 프로그램 실행시에 기존에 저장된 회원들의 정보를 메모리에 가져와서 기존 정보를 활용하기 위한 처리가 필요로 합니다.
	 */
	public void loadMemberDataFile() {
		
	}
	
	
	/**
	 * <pre>
	 * 회원자료저장구조 메모리에 있는 회원정보 파일에 저장하기
	 * 메모리에 저장된 자료는 프로그램이 종료되면 휘발성이므로 모두 사라지게 됩니다.
	 * 따라서 파일, 데이터베이스 등을 이용하여 영속적으로 저장해서 활용하기위한 처리가 필요로 합니다.
	 * </pre>
	 */
	public void saveMemberDataFile() {
		
	}
	
	/**
	 * <pre>
	 * 프로그램 서비스전 초기화 작업 위한 메뉴
	 * </pre>
	 */
	public void initMenu() {
//		int count = 0;
//		count = service.initMember();
//		System.out.print("[회원 초기화 작업이 완료되었습니다. 현재 등록 회원수 (" + count + ")명]");
//		// 프로그램 서비스 전 기존 회원정보 파일 가져와서 회원관리 저장구조 메모리에 저장하기
		
	}
	
	/** 
	 * <pre>
	 * 프로그램 종료 메뉴
	 * </pre>
	*/
	public void exitMenu() {
		// 프로그램 종료전에 회원정보 파일 저장
		// saveMemberDataFile();
			
		Utility.printLine("프로그램 정상 종료");
		System.exit(0);
	}

	/**
	 * <pre>
	 * 문자열 입력 반환 메서드
	 * </pre>
	 * @return 입력 문자열 반환
	 */
	public String inputString() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
		
	/**
	 * <pre>
	 * 숫자 입력 반환 메서드
	 * </pre>
	 * @return 입력 숫자 반환
	 */
	public int inputNumber() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(data);
			
	}
}
