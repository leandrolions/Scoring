package ar.com.nat.scoring.servicesimpl;

import ar.com.nat.scoring.constantes.LogsConstantes;
import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.restconsuming.HttpRestConsuming;
import ar.com.nat.scoring.services.ServicesComunMethods;

public class SimulaBantotalRest extends ServicesComunMethods implements ConsultasRest{

	@Override
	public String Consultas(ConsultaOfertaRequest offerequest, ConsultaOfertaResponse offeresponse,
			HttpRestConsuming httpconsult, RestURLConsulting url) throws ExceptionFallaRestConsult {
		offerequest.setPaso(14116);
		String resp = "";
		try {
			resp = httpconsult.PostHttp(url.getURL_SIMULAR(),offeresponse);
		}catch (Exception e) {
			LogsConstantes.logRest.error("Error consulta SIMULAR"+e.getMessage());
			throw new ExceptionFallaRestConsult("Falla en la consulta de datos");
		}
		return resp;
	}

}
