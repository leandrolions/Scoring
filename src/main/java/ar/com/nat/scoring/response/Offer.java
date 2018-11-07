package ar.com.nat.scoring.response;

import java.io.Serializable;
import java.math.BigInteger;

import ar.com.nat.scoring.dao.querymappers.NativeQueryResultColumn;
import ar.com.nat.scoring.dao.querymappers.NativeQueryResultEntity;

//@Entity
@NativeQueryResultEntity
public class Offer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Id
	@NativeQueryResultColumn(index=0)
	BigInteger OFE_SOL_Nro;
	@NativeQueryResultColumn(index=1)
//	@Column(name="OFE_Nro")
    BigInteger offer_id;
	@NativeQueryResultColumn(index=2)
//	@Column(name="OFE_Monto")
    Float offer_amount;
	@NativeQueryResultColumn(index=3)
//	@Column(name="OFE_Placuotas")
    Integer offer_term;
	@NativeQueryResultColumn(index=4)
//	@Column(name="OFE_Cuota")
    Float offer_fee;
	@NativeQueryResultColumn(index=5)
//	@Column(name="OFE_TEA")
    Float offer_ear;
	
	public BigInteger getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(BigInteger offer_id) {
		this.offer_id = offer_id;
	}
	public Float getOffer_amount() {
		return offer_amount;
	}
	public void setOffer_amount(Float offer_amount) {
		this.offer_amount = offer_amount;
	}
	public Float getOffer_fee() {
		return offer_fee;
	}
	public void setOffer_fee(Float offer_fee) {
		this.offer_fee = offer_fee;
	}
	public Integer getOffer_term() {
		return offer_term;
	}
	public void setOffer_term(Integer offer_term) {
		this.offer_term = offer_term;
	}
	public Float getOffer_ear() {
		return offer_ear;
	}
	public void setOffer_ear(Float offer_ear) {
		this.offer_ear = offer_ear;
	}

}
