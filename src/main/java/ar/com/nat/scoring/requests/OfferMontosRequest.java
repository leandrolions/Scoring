package ar.com.nat.scoring.requests;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Consulta de rango de montos disponibles")
public class OfferMontosRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ApiModelProperty(notes = "Id de Persona",required=true)
	private Integer id;
	@NotNull
	@ApiModelProperty(notes = "Id de Session",required=true)
	private String session_id; 

	@JsonIgnore
	private Date date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
