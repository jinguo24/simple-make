package com.lenovo.simple.parse.exception;

public class SimpleParserNotFoundException extends Throwable {

	private static final long serialVersionUID = 1L;

	public SimpleParserNotFoundException(Throwable t,String msg) {
		super(msg, t);
	}
	
	public SimpleParserNotFoundException(Throwable t) {
		super(t);
	}
	
	public SimpleParserNotFoundException() {
		super();
	}
	
	public SimpleParserNotFoundException(String msg) {
		super(msg);
	}
	
}
