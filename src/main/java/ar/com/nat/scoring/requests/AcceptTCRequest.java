package ar.com.nat.scoring.requests;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="Terminos y condiciones")
public class AcceptTCRequest implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "Id de Persona",required=true)
	  Integer id;
	@ApiModelProperty(notes = "Nro Solicitud",required=true)
	  Integer request_id;
	@ApiModelProperty(notes = "Acepta?",required=true)
	  boolean accept;
	@ApiModelProperty(notes = "Id de Session",required=true)
	  String  session_id;
	  
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
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}


}
