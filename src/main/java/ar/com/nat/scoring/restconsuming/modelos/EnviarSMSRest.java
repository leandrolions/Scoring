package ar.com.nat.scoring.restconsuming.modelos;

import java.io.Serializable;

import ar.com.nat.scoring.dao.querymappers.NativeQueryResultColumn;
import ar.com.nat.scoring.dao.querymappers.NativeQueryResultEntity;
@NativeQueryResultEntity
public class EnviarSMSRest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NativeQueryResultColumn(index=0)
	public String destination;
	@NativeQueryResultColumn(index=1)
	public String message;
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
