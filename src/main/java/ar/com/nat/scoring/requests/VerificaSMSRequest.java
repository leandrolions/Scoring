package ar.com.nat.scoring.requests;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Verifica Envio de SMS")
public class VerificaSMSRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull @NotBlank
	@ApiModelProperty(notes="Id de Persona",required=true)
	private BigInteger id;
	@NotNull @NotBlank
	@ApiModelProperty(notes="Nro de solicitud",required=true)
	private BigInteger request_id;
	@ApiModelProperty(notes="Codigo de Oferta",required=true)
	@NotNull @NotBlank
	private BigInteger offer_id;
	@NotNull @NotBlank
	@ApiModelProperty(notes="Codigo de SMS",required=true)
	private BigInteger sms_code;
	@NotNull @NotBlank
	@ApiModelProperty(notes="Id de Session",required=true)
	private BigInteger session_id;
	@JsonIgnore
	private Date date;

	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getRequest_id() {
		return request_id;
	}
	public void setRequest_id(BigInteger request_id) {
		this.request_id = request_id;
	}
	public BigInteger getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(BigInteger offer_id) {
		this.offer_id = offer_id;
	}
	public BigInteger getSms_code() {
		return sms_code;
	}
	public void setSms_code(BigInteger sms_code) {
		this.sms_code = sms_code;
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
