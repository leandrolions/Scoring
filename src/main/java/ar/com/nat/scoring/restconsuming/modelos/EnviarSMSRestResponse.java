package ar.com.nat.scoring.restconsuming.modelos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnviarSMSRestResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
	String EnvioSmsResult;

	public String getEnvioSmsResult() {
		return EnvioSmsResult;
	}

	public void setEnvioSmsResult(String envioSmsResult) {
		EnvioSmsResult = envioSmsResult;
	}
}
