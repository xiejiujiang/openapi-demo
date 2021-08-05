package com.chanjet.openapi.demo.exception;
/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/

public abstract class ErrorCodeException extends RuntimeException {


	private static final long serialVersionUID = -7873764873091460060L;

	private final String hint;
	public ErrorCodeException(String msg){
		super(msg);
		this.hint = "";
	}
	public ErrorCodeException(String msg,String hint){
		super(msg);
		this.hint = hint;
	}

	public ErrorCodeException(String msg, Throwable e){
		super(msg,e);
		this.hint = "";
	}
	public ErrorCodeException(String msg,String hint, Throwable e){
		super(msg,e);
		this.hint = hint;
	}
	public abstract  int getErrorCode() ;



	public String getHint() {
		return hint;
	}
}
