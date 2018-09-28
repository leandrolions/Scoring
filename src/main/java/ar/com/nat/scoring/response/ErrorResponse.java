package ar.com.nat.scoring.response;

import java.io.Serializable;

public class ErrorResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer code;
	String message;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
