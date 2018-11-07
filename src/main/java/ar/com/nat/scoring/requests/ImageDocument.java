package ar.com.nat.scoring.requests;

import java.io.Serializable;

public class ImageDocument implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer code;
	String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
