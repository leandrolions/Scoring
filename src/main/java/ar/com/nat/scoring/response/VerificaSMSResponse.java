package ar.com.nat.scoring.response;

import java.io.Serializable;

public class VerificaSMSResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	Integer request_id;
	String message;
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRequest_id() {
		return request_id;
	}
	public void setRequest_id(Integer request_id) {
		this.request_id = request_id;
	}

}
