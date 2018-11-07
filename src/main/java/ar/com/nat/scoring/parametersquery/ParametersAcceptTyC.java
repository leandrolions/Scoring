package ar.com.nat.scoring.parametersquery;

import java.math.BigInteger;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.daointerf.ISetQueryParameters;
import ar.com.nat.scoring.requests.AcceptTCRequest;
import ar.com.nat.scoring.response.AcceptTCResponse;

public class ParametersAcceptTyC extends TypesMethods implements ISetQueryParameters{

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
		query.registerStoredProcedureParameter("PER_Nro",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("CRE_Nro",Integer.class,ParameterMode.IN);
		query.setParameter("PER_Nro",  setBigInteger(consul.getId()));
		query.setParameter("SOL_Nro", setBigInteger(consul.getSession_id()));
		query.setParameter("CRE_Nro", consul.getRequest_id());


		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		AcceptTCResponse acceptresp = new AcceptTCResponse();
		acceptresp.setMessage((Integer)query.getOutputParameterValue("ERR_ErrorNro"));
		return (T) acceptresp;
		
	}


}
