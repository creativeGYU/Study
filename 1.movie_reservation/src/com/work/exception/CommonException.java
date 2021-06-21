package com.work.exception;

public class CommonException extends Exception {
	public CommonException() {
		super("사용자 예외");
	}
	
	public CommonException(String message) {
		super("[오류] " + message);
	}	
}
