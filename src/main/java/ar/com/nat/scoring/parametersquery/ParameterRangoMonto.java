package ar.com.nat.scoring.parametersquery;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.daointerf.ISetQueryParameters;
import ar.com.nat.scoring.requests.OfferMontosRequest;
import ar.com.nat.scoring.response.OfferMontosResponse;
import ar.com.nat.scoring.response.Range;

public class ParameterRangoMonto extends TypesMethods implements ISetQueryParameters{

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
		query.registerStoredProcedureParameter("PER_Nro",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("PER_Ingresos",Float.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("amount_min",Float.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("amount_max",Float.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("step",Float.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("fee_min",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("fee_max",Integer.class,ParameterMode.OUT);
		query.setParameter("PER_Nro", setBigInteger(consul.getId()));
		query.setParameter("PER_Ingresos", null);
		query.setParameter("session_id", setBigInteger(consul.getSession_id()));
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
		return (T) offer;
		
	}

}
