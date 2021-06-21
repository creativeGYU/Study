package com.work.exception;

public class DuplicateException extends Exception {
	public DuplicateException() {
		super("중복 예외");
	}
	
	public DuplicateException(String message) {
		super("중복 예외 : " + message);
	}	
}
