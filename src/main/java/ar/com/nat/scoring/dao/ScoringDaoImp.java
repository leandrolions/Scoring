package ar.com.nat.scoring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import ar.com.nat.scoring.daointerf.ISetQueryParameters;
import ar.com.nat.scoring.daointerf.ScoringDao;

@Component
@Transactional
public class ScoringDaoImp implements ScoringDao{
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> ExecSP(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception {
        try {
        	StoredProcedureQuery stored = this.em.createStoredProcedureQuery(Named,salida);
        	stored = setqueryparameters(stored, classeparamns, Parametro);
        	stored.execute();
        	return stored.getResultList();
        }
        catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
	
	public <T> T ExecStored(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception {
        try {
        	StoredProcedureQuery stored = this.em.createStoredProcedureQuery(Named);
        	stored = setqueryparameters(stored, classeparamns, Parametro);
        	stored.execute();
        	return  getOutputParameters(stored, classeparamns);
        }
        catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
	
	private StoredProcedureQuery setqueryparameters(StoredProcedureQuery stored,Class<?> classeparamns,Object...parameterTypes) throws InstantiationException, IllegalAccessException {
		ISetQueryParameters parameters = (ISetQueryParameters)classeparamns.newInstance();
		stored = parameters.setParameters(stored, parameterTypes);
	return stored;
}
	private <T> T getOutputParameters(StoredProcedureQuery stored,Class<?> classeparamns) throws InstantiationException, IllegalAccessException {
		ISetQueryParameters params = (ISetQueryParameters)classeparamns.newInstance();
		return params.getParameters(stored);
	}

}
