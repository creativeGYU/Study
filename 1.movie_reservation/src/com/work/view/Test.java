package com.work.view;

public class Test {

	/**
	 * <pre>
	 * 회원관리 시스템 CUI 시작 메서드
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * <pre>
		 * 회원관리 메뉴
		 * </pre>
		 */
		MemberMenu view = new MemberMenu();
		
		/**
		 * <pre>
		 * 초기화 메뉴 수행
		 * </pre>
		 */
		view.initMenu();
		
		while(true) {
			view.mainMenu();
		
		}
	}
}
