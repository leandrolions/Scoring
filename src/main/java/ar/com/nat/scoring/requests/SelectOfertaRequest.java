package ar.com.nat.scoring.requests;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="Oferta elegida")
public class SelectOfertaRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "Id de Persona",required=true)
	Integer id;
	@ApiModelProperty(notes = "Nro Solicitud",required=true)
	Integer request_id;
	@ApiModelProperty(notes = "Nro de Oferta",required=true)
	Integer offer_id;
	@ApiModelProperty(notes = "Id de Session",required=true)
	String session_id;
	
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
	public Integer getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(Integer offer_id) {
		this.offer_id = offer_id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
}
