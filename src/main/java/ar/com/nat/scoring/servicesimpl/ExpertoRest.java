package ar.com.nat.scoring.servicesimpl;

import ar.com.nat.scoring.constantes.LogsConstantes;
import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.restconsuming.HttpRestConsuming;
import ar.com.nat.scoring.services.ServicesComunMethods;

public class ExpertoRest extends ServicesComunMethods  implements ConsultasRest{


	@Override
	public String Consultas(ConsultaOfertaRequest offerequest, ConsultaOfertaResponse offeresponse,
			HttpRestConsuming httpconsult,RestURLConsulting url) throws ExceptionFallaRestConsult {
		String resp = "";
		offerequest.setPaso(14109);
		try {
			resp = httpconsult.GetHttp(formateoURL(url.getURL_EXPERTO(),offeresponse.getCedula().toString()));
		}catch (Exception e) {
			LogsConstantes.logRest.error("Error consulta EXPERTO"+e.getMessage());
			throw new ExceptionFallaRestConsult("Falla en la consulta de datos");
		}
		return resp;
	}

}
