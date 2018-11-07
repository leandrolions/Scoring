package ar.com.nat.scoring.dao;

import javax.persistence.StoredProcedureQuery;

public interface ISetQueryParameters {
	
	public StoredProcedureQuery setParameters(StoredProcedureQuery stored, Object...objects);
	public <T> T getParameters(StoredProcedureQuery query);


}
