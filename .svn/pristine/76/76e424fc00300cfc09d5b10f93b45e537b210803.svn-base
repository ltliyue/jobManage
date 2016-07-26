package com.inspur.bigdata.hbaseio.exceptions;

/**
 * 
 * @author maolh
 *
 */
public class RowkeyGenerateException extends Exception{
	private String message;
	private Throwable throwable;
	public RowkeyGenerateException(String message){
		this.message = message;
	}
	public RowkeyGenerateException(Throwable throwable){
		this.throwable = throwable;
	}
	public RowkeyGenerateException(Throwable throwable,String message){
		this.throwable = throwable;
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
}
