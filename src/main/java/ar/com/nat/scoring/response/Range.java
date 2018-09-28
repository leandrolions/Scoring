package ar.com.nat.scoring.response;

public class Range {
	
	Float amount_min;
	Float amount_max;
	Float step;
	Integer fee_min;
	Integer fee_max;
	
	public Float getAmount_min() {
		return amount_min;
	}
	public void setAmount_min(Float amount_min) {
		this.amount_min = amount_min;
	}
	public Float getAmount_max() {
		return amount_max;
	}
	public void setAmount_max(Float amount_max) {
		this.amount_max = amount_max;
	}
	public Float getStep() {
		return step;
	}
	public void setStep(Float step) {
		this.step = step;
	}
	public Integer getFee_min() {
		return fee_min;
	}
	public void setFee_min(Integer fee_min) {
		this.fee_min = fee_min;
	}
	public Integer getFee_max() {
		return fee_max;
	}
	public void setFee_max(Integer fee_max) {
		this.fee_max = fee_max;
	}

}
