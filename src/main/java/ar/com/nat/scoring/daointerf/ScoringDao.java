package ar.com.nat.scoring.daointerf;

import java.util.List;

public interface ScoringDao {

//	public List<Object> ExecSP(String Named,Class<?> nombre,Class<?> destino,Object... Parametro) throws Exception;
	public <T> List<T> ExecSP(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception ;
	public <T> T ExecStored(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception;
}
