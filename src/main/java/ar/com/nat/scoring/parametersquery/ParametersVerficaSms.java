package ar.com.nat.scoring.parametersquery;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.daointerf.ISetQueryParameters;
import ar.com.nat.scoring.requests.VerificaSMSRequest;
import ar.com.nat.scoring.response.VerificaSMSResponse;

public class ParametersVerficaSms implements ISetQueryParameters{
	
	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		VerificaSMSRequest versms= new VerificaSMSRequest();

		if(objects[0] instanceof VerificaSMSRequest) {
			versms = (VerificaSMSRequest)objects[0];
		}
		else {
			throw new RuntimeException();
		}
		
		
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("CRE_Nro",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("PIN",String.class,ParameterMode.OUT);
	
		query.setParameter("PER_Nro", versms.getId());
		query.setParameter("CRE_Nro", versms.getRequest_id());
		query.setParameter("session_id", versms.getSession_id());
		query.setParameter("date", new Date());

		
		
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getParameters(StoredProcedureQuery query) {
		VerificaSMSResponse response = new VerificaSMSResponse();
//		response.setId((String) query.getOutputParameterValue("ERR_ErrorTipo"));
		response.setMessage((String) query.getOutputParameterValue("ERR_ErrorMsg"));
		return (T) response;
	}


}
