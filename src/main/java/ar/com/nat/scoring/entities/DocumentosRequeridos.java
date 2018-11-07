package ar.com.nat.scoring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Documentosrequeridos")
public class DocumentosRequeridos {
	
	@Column(name="DOR_SOL_Nro")
	Integer sol_nro;
	@Column(name="DOR_PER_Nro")
	Integer per_nro;
	@Column(name="DOR_Code")
	Integer code;
	@Id
	@Column(name="DOR_URL")
	String url;
	
	public Integer getSol_nro() {
		return sol_nro;
	}
	public void setSol_nro(Integer sol_nro) {
		this.sol_nro = sol_nro;
	}
	public Integer getPer_nro() {
		return per_nro;
	}
	public void setPer_nro(Integer per_nro) {
		this.per_nro = per_nro;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	


}
