package com.work.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <pre>
 * 공통으로 사용하는 메서드를 위한 유틸리티 클래스
 * 모든 메서드는 객체생성 없이 사용가능하도록 Class Method 선언(Static method)
 * </pre>
 * @author 이태규
 * @version ver.1.0
 * @since jdk1.8
 */
public class Utility {
	
	/**
	 * <pre>
	 * 현재날짜 기본형식의 문자열로 변환 반환하는 메서드
	 * 기본형식 : 년도4자리-월2자리-일2자리
	 * </pre>
	 * @return 기본 형식의 현재날짜 문자열
	 */
	public static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date todate = new Date();
		return dateFormat.format(todate);
	}
	
	/** 
	 * <pre>
	 * 출력시 화면 상단 출력위한 메서드
	 * </pre>
	 * @param message 상단 메뉴이름
	 */ 
	public static void printLine(String message) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println("|" + message + "|");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}
	
	/**
	 * <pre>
	 * 지정길이 랜덤숫자 반환
	 * </pre>
	 * @param length 랜덤 숫자 길이
	 * @return 지정길이숫자 반환
	 */
	public static String getSecureNumberString(int length) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for (int index = 0; index < length; index++) {
			secureString.append(random.nextInt(10));
		}
		return secureString.toString();
	}
}
