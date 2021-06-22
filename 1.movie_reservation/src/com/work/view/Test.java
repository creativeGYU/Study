package com.work.view;

import com.work.exception.DuplicateException;
import com.work.model.service.MemberService;

public class Test {

	/**
	 * <pre>
	 * 회원관리 시스템 CUI 시작 메서드
	 * @param args
	 * @throws DuplicateException
	 */
	public static void main(String[] args) throws DuplicateException{
		
		/**
		 * <pre>
		 * 회원관리 메뉴
		 * </pre>
		 */
		MemberMenu view = new MemberMenu();
		
		/**
		 * 
		 */
		MemberService service = new MemberService();
		
		/**
		 * <pre>
		 * 초기화 메뉴 수행
		 * </pre>
		 */
		service.initialMember();
		
		while(true) {
			view.mainMenu();
		
		}
	}
}
