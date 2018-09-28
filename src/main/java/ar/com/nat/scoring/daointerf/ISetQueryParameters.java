package ar.com.nat.scoring.daointerf;

import javax.persistence.StoredProcedureQuery;

public interface ISetQueryParameters {
	
	public StoredProcedureQuery setParameters(StoredProcedureQuery stored, Object...objects);
	public <T> T getParameters(StoredProcedureQuery query);


}
