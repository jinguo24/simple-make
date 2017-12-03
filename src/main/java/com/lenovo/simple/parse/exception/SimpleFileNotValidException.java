package com.lenovo.simple.parse.exception;

public class SimpleFileNotValidException extends Throwable {

	private static final long serialVersionUID = 1L;

	public SimpleFileNotValidException(Throwable t,String msg) {
		super(msg, t);
	}
	
	public SimpleFileNotValidException(Throwable t) {
		super(t);
	}
	
	public SimpleFileNotValidException() {
		super();
	}
	
	public SimpleFileNotValidException(String msg) {
		super(msg);
	}
}
