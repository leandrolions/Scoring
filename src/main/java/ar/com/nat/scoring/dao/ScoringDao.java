package ar.com.nat.scoring.dao;

import java.util.List;

public interface ScoringDao {

	public <T> List<T> ExecSP(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception ;
	public <T> T ExecStored(String Named,Class<?> classeparamns,Class<T> salida,Object... Parametro) throws Exception;
	public <T> T ExecStored(String Named,Class<?> classeparamns,Class<T> salida,Class<?> lista,Object... Parametro) throws Exception;
}
