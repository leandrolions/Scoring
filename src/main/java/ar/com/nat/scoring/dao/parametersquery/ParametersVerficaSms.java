package ar.com.nat.scoring.dao.parametersquery;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.dao.ISetQueryParameters;
import ar.com.nat.scoring.requests.VerificaSMSRequest;
import ar.com.nat.scoring.response.VerificaSMSResponse;
import ar.com.nat.scoring.restconsuming.modelos.EnviarSMSRest;

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
		query.registerStoredProcedureParameter("PER_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("CRE_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("PIN",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("TEL_Numero",Double.class,ParameterMode.OUT);
	
		query.setParameter("PER_Nro",  versms.getId());
		query.setParameter("CRE_Nro", versms.getRequest_id());
		query.setParameter("session_id", versms.getSession_id());
		query.setParameter("date", new Date());

		
		
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getParameters(StoredProcedureQuery query) {
		VerificaSMSResponse response = new VerificaSMSResponse();
		EnviarSMSRest sms = new EnviarSMSRest();
		response.setMessage((String) query.getOutputParameterValue("ERR_ErrorMsg"));
		sms.setDestination(String.valueOf((Double)query.getOutputParameterValue("TEL_Numero")));
		sms.setMessage((String)query.getOutputParameterValue("PIN"));
		response.setSms(sms);
		return (T) response;
	}


}
