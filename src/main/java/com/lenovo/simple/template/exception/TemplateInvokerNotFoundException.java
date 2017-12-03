package com.lenovo.simple.template.exception;

public class TemplateInvokerNotFoundException extends Throwable {

	private static final long serialVersionUID = 1L;

	public TemplateInvokerNotFoundException(Throwable t,String msg) {
		super(msg, t);
	}
	
	public TemplateInvokerNotFoundException(Throwable t) {
		super(t);
	}
	
	public TemplateInvokerNotFoundException() {
		super();
	}
	
	public TemplateInvokerNotFoundException(String msg) {
		super(msg);
	}
	
}
