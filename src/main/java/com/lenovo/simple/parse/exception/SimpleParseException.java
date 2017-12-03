package com.lenovo.simple.parse.exception;

public class SimpleParseException extends Throwable {

	private static final long serialVersionUID = 1L;

	public SimpleParseException(Throwable t,String msg) {
		super(msg, t);
	}
	
	public SimpleParseException(Throwable t) {
		super(t);
	}
	
	public SimpleParseException() {
		super();
	}
	
	public SimpleParseException(String msg) {
		super(msg);
	}
}
