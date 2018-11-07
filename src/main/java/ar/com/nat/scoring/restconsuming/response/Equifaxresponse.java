package ar.com.nat.scoring.restconsuming.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Equifaxresponse implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4151794949810018111L;
	
	
	Integer code;
	@JsonProperty("message")
	String mensaje;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
