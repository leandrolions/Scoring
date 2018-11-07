package ar.com.nat.scoring.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="Respuesta Terminos y condiciones")
public class AcceptTCResponse implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "Id de Persona",required=true)
	  Integer id;
	@ApiModelProperty(notes = "Nro Solicitud",required=true)
	  Integer request_id;
	@ApiModelProperty(notes = "Mesaje",required=true)
	  Integer message;
	  
	  
	public Integer getRequest_id() {
		return request_id;
	}
	public void setRequest_id(Integer request_id) {
		this.request_id = request_id;
	}
	public Integer getMessage() {
		return message;
	}
	public void setMessage(Integer message) {
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
