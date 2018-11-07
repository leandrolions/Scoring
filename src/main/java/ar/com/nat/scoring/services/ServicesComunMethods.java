package ar.com.nat.scoring.services;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import ar.com.nat.scoring.dao.parametersquery.TypesMethods;

public class ServicesComunMethods {

	/**
	 * 
	 * @param URL con s% para reemplazar
	 * @param parametro a ser reemplazado
	 * @return devuelve la url formateada
	 */
	protected String formateoURL(String URL,String parametro) {

		return String.format(URL,parametro);
	}
	/**
	 * 
	 * @param URL entrade de URL a ingresar queries
	 * @param parametro parametros de las queries
	 * @return devuelve la URL con los queries formateados
	 */
	protected String formateoURLQuery(String URL,Object...parametro) {
		
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(URL)
				.queryParam("numero", TypesMethods.setString(parametro[0]))
				.queryParam("periodo_yyyymm", TypesMethods.setString(parametro[1]))
				.queryParam("pais", TypesMethods.setString(parametro[2]))
				.queryParam("tipo", TypesMethods.setString(parametro[3]));

		return builder.toUriString();
	}
	
	protected <T> LinkedMultiValueMap<String, Object> creamap(Object paramsentrada, Class<T> classentrada) throws Exception{
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		@SuppressWarnings("unchecked")
		T io = (T)paramsentrada;
		for(Field field : paramsentrada.getClass().getDeclaredFields()) {
		    try {
		    	String modificador = Modifier.toString(field.getModifiers());
		    	if(modificador.equalsIgnoreCase("public") || modificador.equals("")) {
		    		if(field.getName().contains("String")) {
		    			params.add(field.getName(), String.valueOf(field.get(io)));
		    		}else {
		    			params.add(field.getName(), field.get(io));
		    		}
		    	}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new Exception("Error in method create params to post");
			}
		}
		return params;
	}
	/**
	 * 
	 * @param URL
	 * @param paramsentrada classe con los datos cargados
	 * @param classentrada classe de los parametros de entrada
	 * @return URL con queries strings
	 * @throws Exception errores de creacion de la clase de entrada
	 */
	protected <T> String formateoURLQuery(String URL,Object paramsentrada, Class<T> classentrada) throws Exception {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL);
		@SuppressWarnings("unchecked")
		T io = (T)paramsentrada;
		for(Field field : paramsentrada.getClass().getDeclaredFields()) {
		    try {
		    	String modificador = Modifier.toString(field.getModifiers());
		    	if(modificador.equalsIgnoreCase("public") || modificador.equals("")) {
		    		builder.queryParam(field.getName(), TypesMethods.setString(field.get(io)));
		    	}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new Exception("Error in method create params to post");
			}
		}
		return builder.toUriString();
	}
}
