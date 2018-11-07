package ar.com.nat.scoring.parametersquery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.constantes.VariablesEstaticas;
import ar.com.nat.scoring.daointerf.ISetQueryParameters;
import ar.com.nat.scoring.requests.DocumentsRequest;
import ar.com.nat.scoring.response.Document;
import ar.com.nat.scoring.response.DocumentResponse;

public class ParametersDocumentos extends TypesMethods implements ISetQueryParameters{

	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		
		DocumentsRequest select= new DocumentsRequest();
		if(objects[0] instanceof DocumentsRequest) {
			select = (DocumentsRequest)objects[0];
		}
		else {
			throw new RuntimeException();
		}
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("date",Date.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro_Out",Integer.class,ParameterMode.OUT);

		query.setParameter("PER_Nro",  select.getId());
		query.setParameter("session_id", select.getSession_id());
		query.setParameter("SOL_Nro", select.getRequest_id());
		query.setParameter("date", new Date());

		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		DocumentResponse documents = new DocumentResponse();
		List<Document> doc = new ArrayList<Document>();
		documents.setRequest_id(((Integer)query.getOutputParameterValue("SOL_Nro_Out")));
		documents.setMessage((String)query.getOutputParameterValue("ERR_ErrorMsg"));
		try {
			doc = query.getResultList();
		}catch (Exception e) {
			VariablesEstaticas.log.error("no fue posible obtener lista de documentos:"+e.getMessage());
			doc = new ArrayList<Document>();
		}
		documents.setDocuments(doc);

		return (T) documents;
		
	}
}
