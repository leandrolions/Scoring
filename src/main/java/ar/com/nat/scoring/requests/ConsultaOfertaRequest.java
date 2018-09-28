package ar.com.nat.scoring.requests;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Consulta de rango de montos disponibles")
public class ConsultaOfertaRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ApiModelProperty(notes = "Id de Persona",required=true)
	private BigInteger id;
	@NotNull
	@ApiModelProperty(notes = "Id de Session",required=true)
	private BigInteger session_id;
	@JsonIgnore
	private Date date;
	
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getSession_id() {
		return session_id;
	}
	public void setSession_id(BigInteger session_id) {
		this.session_id = session_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
