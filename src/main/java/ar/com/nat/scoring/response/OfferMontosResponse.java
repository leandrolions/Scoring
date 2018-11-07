package ar.com.nat.scoring.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Montos ofertados",subTypes= {Range.class})
public class OfferMontosResponse {
	
	@ApiModelProperty(notes="Id de Persona")
	Integer id;
	@ApiModelProperty(notes="Range")
	Range range;
	@ApiModelProperty(notes="mensajes generales")
	transient String message;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Range getRange() {
		return range;
	}
	public void setRange(Range range) {
		this.range = range;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	
}
