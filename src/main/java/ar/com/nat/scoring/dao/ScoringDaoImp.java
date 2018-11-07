package ar.com.nat.scoring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ar.com.nat.scoring.entities.DocumentosRequeridos;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaDataBase;
import ar.com.nat.scoring.exception.customexception.ExceptionNotFound;
import ar.com.nat.scoring.requests.DocumentsRequest;

@Repository
@Transactional
public class ScoringDaoImp implements ScoringDao{
	
	@PersistenceContext
	EntityManager em;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public <T> List<T> ExecSP(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception {
		List<T> classedesalida;
        try {
        	StoredProcedureQuery stored = this.em.createStoredProcedureQuery(Named,salida);
        	stored = setqueryparameters(stored, classeparamns, Parametro);
        	stored.execute();
        	classedesalida = stored.getResultList();
        	if(classedesalida == null) {
        		throw new ExceptionNotFound("Not Found in database");
        	}
			return classedesalida;
        }
        catch (Exception e) {
        	log.error("errror de conexion a data base:"+e.getMessage());
        	throw new ExceptionFallaDataBase("Procedure Problems:"+e.getMessage());
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
        	log.error("errror de conexion a data base:"+e.getMessage());
			throw new ExceptionFallaDataBase("Procedure Problems:"+e.getMessage());
		}
        catch (Exception e) {
        	log.error("errror no esperado en la ejecucion del procedure "+Named+":"+e.getMessage());
			return salida.newInstance();
		}
    }
	public <T> T ExecStored(String Named,Class<?> classeparamns,Class<T> salida,Class<?> lista,Object... Parametro) throws Exception {
		T classesalida;
        try {
        	StoredProcedureQuery stored = this.em.createStoredProcedureQuery(Named,lista);
        	stored = setqueryparameters(stored, classeparamns, Parametro);
        	stored.execute();
        	classesalida= getOutputParameters(stored, classeparamns);
        	if(classesalida == null) {
        		throw new ExceptionNotFound("Not Found in database");
        	}
        	return classesalida;
        }
        catch (PersistenceException e) {
        	throw new ExceptionFallaDataBase("Procedure Problems:"+e.getMessage());
		}
        catch (Exception e) {
        	log.error("errror no esperado en la ejecucion del procedure "+Named+":"+e.getMessage());
			return salida.newInstance();
		}
    }
	
	private StoredProcedureQuery setqueryparameters(StoredProcedureQuery stored,Class<?> classeparamns,Object...parameterTypes) throws InstantiationException, IllegalAccessException {
		ISetQueryParameters parameters = (ISetQueryParameters)classeparamns.newInstance();
		stored = parameters.setParameters(stored, parameterTypes);
			if(parameterTypes[0]  instanceof DocumentsRequest) {
				DocumentsRequest docre = (DocumentsRequest) parameterTypes[0];
				for(DocumentosRequeridos req : docre.getDocs()) {
					InsertOrUpdate(req);
				}
			}
	return stored;
}
	private <T> T getOutputParameters(StoredProcedureQuery stored,Class<?> classeparamns) throws InstantiationException, IllegalAccessException {
		ISetQueryParameters params = (ISetQueryParameters)classeparamns.newInstance();
		return params.getParameters(stored);
	}

	public void InsertOrUpdate(Object ins) {
		this.em.merge(ins);
	}
}
