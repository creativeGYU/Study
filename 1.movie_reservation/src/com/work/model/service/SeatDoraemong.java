package com.work.model.service;

import java.util.Scanner;

import oracle.net.aso.i;

public class SeatDoraemong {
	Scanner scan = new Scanner(System.in);
	
	String[][] seat = new String[7][10];

//	String[] ColumnNumberSeat = new String[10];
//	String[] ASeat = new String[10];
//	String[] BSeat = new String[10];
//	String[] CSeat = new String[10];
//	String[] DSeat = new String[10];
//	String[] ESeat = new String[10];
//	String[] FSeat = new String[10];
	
	/**
	 * <pre>
	 * 좌석 기본 생성자
	 * </pre>
	 */
	public SeatDoraemong() {
		for (int i = 0; i < 7; i++) {
			switch (i) {
				case 0 : 
					for (int j = 1; j < 10; j++) {
						seat[0][j] = Integer.toString(j);
						System.out.print("  " + seat[0][j]);
					}
					System.out.println();
				break;
			}
			for (int j = 65 + i; j < 66 + i; j++) {
				System.out.print((char)j +" ");
			}
			for (int j = 0; j < 9; j++) {
				seat[i][j] = " □ ";
				System.out.print(seat[i][j]);
			}
				System.out.println();
		}
	}
	
	public void showReserveSeat() {
		System.out.println("현재 예약되어 있는 좌석은 " + "■ 이고,");
		System.out.println("예약 가능한 좌석은 " + "□ 입니다.");
		
		for (int i = 1; i < seat.length; i++) {
			for (int j = 1; j < seat[i].length; j++) {
				System.out.print(seat[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean showOne(String inputRow) {
		switch(inputRow) {
		case "A" : case "a" :
			System.out.println("A행");
			for (int i = 0; i < seat[1].length; i++) {
				System.out.print(seat[1][i]);
			}
			System.out.println();
			return true;
			
		case "B" : case "b" :
			System.out.println("B행");
			for (int i = 0; i < seat[2].length; i++) {
				System.out.print(seat[2][i]);
			}
			System.out.println();
			return true;
			
		case "C" : case "c" :
			System.out.println("C행");
			for (int i = 0; i < seat[3].length; i++) {
				System.out.print(seat[3][i]);
			}
			System.out.println();
			return true;	
			
		case "D" : case "d" :
			System.out.println("D행");
			for (int i = 0; i < seat[4].length; i++) {
				System.out.print(seat[4][i]);
			}
			System.out.println();
			return true;	
			
		case "E" : case "e" :
			System.out.println("E행");
			for (int i = 0; i < seat[5].length; i++) {
				System.out.print(seat[5][i]);
			}
			System.out.println();
			return true;	
		
		case "F" : case "f" :
			System.out.println("F행");
			for (int i = 0; i < seat[6].length; i++) {
				System.out.print(seat[6][i]);
			}
			System.out.println();
			return true;
			
		default :
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			return false;
		}
	}
	
	public void reservation() {
		String inputRow = null;
		int inputColumn = 0;
		String reserveSeat = " ■ ";
		boolean right;
		
		while(true) {
			showReserveSeat();
			System.out.print("원하시는 행을 입력해주세요.");
			inputRow = scan.nextLine();
			
			right = showOne(inputRow);
			
			if(right == false) {
				continue;
			}
			
			System.out.print("원하시는 열을 입력해주세요.");
			inputColumn = scan.nextInt();
			
			switch(inputRow) {
				case "A" : case "a" :
					seat[1][inputColumn-1] = reserveSeat;
					break;
					
				case "B" : case "b" :
					seat[2][inputColumn-1] = reserveSeat;
					break;
					
				case "C" : case "c" :
					seat[3][inputColumn-1] = reserveSeat;
					break;
					
				case "D" : case "d" :
					seat[4][inputColumn-1] = reserveSeat;
					break;
					
				case "E" : case "e" :
					seat[5][inputColumn-1] = reserveSeat;
					break;
					
				case "F" : case "f" :
					seat[6][inputColumn-1] = reserveSeat;
					break;
				
				default :
					System.out.println("없는 좌석입니다. 다시 입력해주세요.");
					continue;
			}
		}
	}
	
	public void delReserve() {
		String deleteRow = null;
		int deleteColumn = 0;
		boolean right;
		String deleteSeatString = " □ ";
		String reserveSeat = " ■ ";
		int count;
		
		while(true) {
			count = 0;
			System.out.print("예약했던 행을 입력해주세요.");
			deleteRow = scan.nextLine();
			
			right = showOne(deleteRow);
			
			if (right == false) {
				continue;
			}
			
			System.out.print("예약했던 열을 입력해주세요.");
			deleteColumn = scan.nextInt();
			
			switch (deleteRow) {
			case "A" : case "a" :
				for (int i = 0; i < seat[1].length; i++) {
					if (seat[1][i].equals(reserveSeat)) {
						seat[1][i] = deleteSeatString;
					} else {
						count += 1;
					}
				}
				
				if (count == seat[1].length) {
					System.out.println("예약되지 않은 좌석입니다. 정보를 다시 확인해주세요.");
					continue;
				}
				break;
				
			case "B" : case "b" :
				for (int i = 0; i < seat[2].length; i++) {
					if (seat[2][i].equals(reserveSeat)) {
						seat[2][i] = deleteSeatString;
					} else {
						count += 1;
					}
				}
				
				if (count == seat[2].length) {
					System.out.println("예약되지 않은 좌석입니다. 정보를 다시 확인해주세요.");
					continue;
				}
				break;	
			
			case "C" : case "c" :
				for (int i = 0; i < seat[3].length; i++) {
					if (seat[3][i].equals(reserveSeat)) {
						seat[3][i] = deleteSeatString;
					} else {
						count += 1;
					}
				}
				
				if (count == seat[3].length) {
					System.out.println("예약되지 않은 좌석입니다. 정보를 다시 확인해주세요.");
					continue;
				}
				break;
				
			case "D" : case "d" :
				for (int i = 0; i < seat[4].length; i++) {
					if (seat[4][i].equals(reserveSeat)) {
						seat[4][i] = deleteSeatString;
					} else {
						count += 1;
					}
				}
				
				if (count == seat[4].length) {
					System.out.println("예약되지 않은 좌석입니다. 정보를 다시 확인해주세요.");
					continue;
				}
				break;	
			
			case "E" : case "e" :
				for (int i = 0; i < seat[5].length; i++) {
					if (seat[5][i].equals(reserveSeat)) {
						seat[5][i] = deleteSeatString;
					} else {
						count += 1;
					}
				}
				
				if (count == seat[5].length) {
					System.out.println("예약되지 않은 좌석입니다. 정보를 다시 확인해주세요.");
					continue;
				}
				break;
			
			case "F" : case "f" :
				for (int i = 0; i < seat[6].length; i++) {
					if (seat[6][i].equals(reserveSeat)) {
						seat[6][i] = deleteSeatString;
					} else {
						count += 1;
					}
				}
				
				if (count == seat[6].length) {
					System.out.println("예약되지 않은 좌석입니다. 정보를 다시 확인해주세요.");
					continue;
				}
				break;
				
			default:
				break;
			}
		}
	}
	
	public void showSeat() {
		for (int i = 0; i < seat[i].length; i++) {
			
		}
	}
	
}	