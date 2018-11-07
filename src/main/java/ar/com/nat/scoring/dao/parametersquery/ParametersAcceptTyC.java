package ar.com.nat.scoring.dao.parametersquery;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.dao.ISetQueryParameters;
import ar.com.nat.scoring.requests.AcceptTCRequest;
import ar.com.nat.scoring.response.AcceptTCResponse;

public class ParametersAcceptTyC implements ISetQueryParameters{

	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		
		AcceptTCRequest consul= new AcceptTCRequest();
		if(objects[0] instanceof AcceptTCRequest) {
			consul = (AcceptTCRequest)objects[0];	
		}
		else {
			throw new RuntimeException();
		}
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("CRE_Nro",Integer.class,ParameterMode.IN); //session_id
		query.registerStoredProcedureParameter("session_id",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.setParameter("PER_Nro",  consul.getId());
		query.setParameter("SOL_Nro", consul.getRequest_id());
		query.setParameter("CRE_Nro", null);
		query.setParameter("date", new Date());
		query.setParameter("session_id", consul.getSession_id());


		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		AcceptTCResponse acceptresp = new AcceptTCResponse();
		acceptresp.setMessage((Integer)query.getOutputParameterValue("ERR_ErrorNro"));
		return (T) acceptresp;
		
	}


}
