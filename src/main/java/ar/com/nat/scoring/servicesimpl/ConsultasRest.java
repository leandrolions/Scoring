package ar.com.nat.scoring.servicesimpl;

import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.restconsuming.HttpRestConsuming;

public interface ConsultasRest {
	
	String Consultas(ConsultaOfertaRequest offerequest,ConsultaOfertaResponse offeresponse,HttpRestConsuming httpconsult,RestURLConsulting url) throws ExceptionFallaRestConsult;

}
