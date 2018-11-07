package ar.com.nat.scoring.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.nat.scoring.restconsuming.modelos.ConsultasBCURest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description="Lista de ofertas")
public class ConsultaOfertaResponse implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(notes = "Id de Persona",required=true)
	Integer id;
	@ApiModelProperty(notes = "Nro Solicitud",required=true)
	Integer request_id;
	@ApiModelProperty(notes = "Lista de Ofertas",required=true)
	transient List<Offer> offer;
	@ApiModelProperty(notes = "Mesaje",required=true)
	String message;
	@JsonIgnore
	transient List<ConsultasBCURest> bcu;
	@JsonIgnore
	transient Integer cedula;
	@JsonIgnore
	transient String consulta;
	
	public List<ConsultasBCURest> getBcu() {
		return bcu;
	}
	public void setBcu(List<ConsultasBCURest> bcu) {
		this.bcu = bcu;
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
	public List<Offer> getOffer() {
		return offer;
	}
	public void setOffer(List<Offer> offer) {
		this.offer = offer;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCedula() {
		return cedula;
	}
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

}
