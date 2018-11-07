package ar.com.nat.scoring.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.nat.scoring.restconsuming.modelos.EnviarSMSRest;
@Entity
public class VerificaSMSResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	Integer id;
	Integer request_id;
	String message;
	@JsonIgnore
	transient EnviarSMSRest sms;
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequest_id() {
		return request_id;
	}
	public void setRequest_id(Integer request_id) {
		this.request_id = request_id;
	}
	public EnviarSMSRest getSms() {
		return sms;
	}
	public void setSms(EnviarSMSRest sms) {
		this.sms = sms;
	}

}
