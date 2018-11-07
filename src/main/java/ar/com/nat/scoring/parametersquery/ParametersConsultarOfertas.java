package ar.com.nat.scoring.parametersquery;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.constantes.VariablesEstaticas;
import ar.com.nat.scoring.daointerf.ISetQueryParameters;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.response.Offer;

public class ParametersConsultarOfertas extends TypesMethods implements ISetQueryParameters{

	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		
		ConsultaOfertaRequest consul= new ConsultaOfertaRequest();
		if(objects[0] instanceof ConsultaOfertaRequest) {
			consul = (ConsultaOfertaRequest)objects[0];
		}
		else {
			throw new RuntimeException();
		}
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",BigInteger.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Monto",Float.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Cuota",Float.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro",BigInteger.class,ParameterMode.OUT);
		query.setParameter("PER_Nro", setBigInteger(consul.getId()));
		query.setParameter("session_id", setBigInteger(consul.getSession_id()));
		query.setParameter("SOL_Monto", setFloat(consul.getAmount()));
		query.setParameter("SOL_Cuota", setFloat(consul.getFee()));
		query.setParameter("date", new Date());

		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		ConsultaOfertaResponse offer = new ConsultaOfertaResponse();
		List<Offer> ofertas = new ArrayList<Offer>();
		try {
			ofertas = (List<Offer>)query.getResultList();
			if(ofertas != null) {
				offer.setOffer(ofertas);
			}
		}catch (Exception e) {
			VariablesEstaticas.log.error("No fue posibles obtener respuesta del procedure:"+e.getMessage());
		}
//		offer.setRequest_id(setInteger(query.getOutputParameterValue("SOL_Nro")));
//		offer.setMessage((Integer)query.getOutputParameterValue("ERR_ErrorNro"));
		return (T) offer;
		
	}

}
