package ar.com.nat.scoring.dao.parametersquery;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.dao.ISetQueryParameters;
import ar.com.nat.scoring.requests.OfferMontosRequest;
import ar.com.nat.scoring.response.OfferMontosResponse;
import ar.com.nat.scoring.response.Range;

public class ParametersRangoMonto implements ISetQueryParameters{

	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		
		OfferMontosRequest consul= new OfferMontosRequest();
		if(objects[0] instanceof OfferMontosRequest) {
			consul = (OfferMontosRequest)objects[0];
		}
		else {
			throw new RuntimeException();
		}
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("amount_min",Float.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("amount_max",Float.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("step",Float.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("fee_min",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("fee_max",Integer.class,ParameterMode.OUT);
		query.setParameter("PER_Nro", consul.getId());
		query.setParameter("session_id",consul.getSession_id());
		query.setParameter("date", new Date());

		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		OfferMontosResponse offer = new OfferMontosResponse();
		Range range = new Range();
		range.setAmount_min((Float)query.getOutputParameterValue("amount_min"));
		range.setAmount_max((Float)query.getOutputParameterValue("amount_max"));
		range.setStep((Float)query.getOutputParameterValue("step"));
		range.setFee_min((Integer)query.getOutputParameterValue("fee_min"));
		range.setFee_max((Integer)query.getOutputParameterValue("fee_max"));
		offer.setRange(range);
		offer.setMessage((String) query.getOutputParameterValue("ERR_ErrorMsg"));
		return (T) offer;
		
	}

}
