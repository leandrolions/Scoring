package ar.com.nat.scoring.requests;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Consulta de ofertas")
public class ConsultaOfertaRequest implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Id de Persona",required=true)
	  Integer id;
	@ApiModelProperty(notes = "Monto",required=true)
	  BigInteger amount;
	  @ApiModelProperty(notes = "Cantidad Cuotas",required=true)
	  BigInteger fee;
	  @ApiModelProperty(notes = "Id de Session",required=true)
	  String session_id;
	  @JsonIgnore
	  transient Integer paso;
	  @JsonIgnore
	  transient Integer per_ban_nro;
	  @JsonIgnore
	  transient Integer sol_nro_procesar;
	  
	public Integer getPaso() {
		return paso;
	}
	public void setPaso(Integer paso) {
		this.paso = paso;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	public BigInteger getFee() {
		return fee;
	}
	public void setFee(BigInteger fee) {
		this.fee = fee;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Integer getPer_ban_nro() {
		return per_ban_nro;
	}
	public void setPer_ban_nro(Integer per_ban_nro) {
		this.per_ban_nro = per_ban_nro;
	}
	public Integer getSol_nro_procesar() {
		return sol_nro_procesar;
	}
	public void setSol_nro_procesar(Integer sol_nro_procesar) {
		this.sol_nro_procesar = sol_nro_procesar;
	}
	  

}
