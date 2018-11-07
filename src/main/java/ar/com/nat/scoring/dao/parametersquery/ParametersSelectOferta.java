package ar.com.nat.scoring.dao.parametersquery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.dao.ISetQueryParameters;
import ar.com.nat.scoring.dao.querymappers.Results;
import ar.com.nat.scoring.requests.SelectOfertaRequest;
import ar.com.nat.scoring.response.Document;
import ar.com.nat.scoring.response.SelectOfertaResponse;

public class ParametersSelectOferta implements ISetQueryParameters{

	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		
		SelectOfertaRequest select= new SelectOfertaRequest();
		if(objects[0] instanceof SelectOfertaRequest) {
			select = (SelectOfertaRequest)objects[0];
		}
		else {
			throw new RuntimeException();
		}
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("OFE_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("CRE_Nro",Integer.class,ParameterMode.OUT);
		query.setParameter("PER_Nro", select.getId());
		query.setParameter("session_id", select.getSession_id());
		query.setParameter("OFE_Nro", select.getOffer_id());
		query.setParameter("SOL_Nro", select.getRequest_id());
		query.setParameter("date", new Date());

		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		SelectOfertaResponse select = new SelectOfertaResponse();
		List<Document> doc = new ArrayList<Document>();
		select.setRequest_id((Integer) (query.getOutputParameterValue("CRE_Nro")));
		select.setMessage((String)query.getOutputParameterValue("ERR_ErrorMsg"));
		try {
			doc = Results.map(query.getResultList(), Document.class);
//			doc = query.getResultList();
			if(doc != null) {
				select.setDocuments(doc);
			}
		}catch (Exception e) {
//			VariablesEstaticas.log.error("no fue posible obtener lista de documentos :"+e);
		}

		return (T) select;
		
	}

}
