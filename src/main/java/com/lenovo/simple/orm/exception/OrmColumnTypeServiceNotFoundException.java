package com.lenovo.simple.orm.exception;

public class OrmColumnTypeServiceNotFoundException extends Throwable {

	private static final long serialVersionUID = 1L;

	public OrmColumnTypeServiceNotFoundException(Throwable t,String msg) {
		super(msg, t);
	}
	
	public OrmColumnTypeServiceNotFoundException(Throwable t) {
		super(t);
	}
	
	public OrmColumnTypeServiceNotFoundException() {
		super();
	}
	
	public OrmColumnTypeServiceNotFoundException(String msg) {
		super(msg);
	}
	
}
