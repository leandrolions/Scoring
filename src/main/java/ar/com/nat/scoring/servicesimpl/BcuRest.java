package ar.com.nat.scoring.servicesimpl;

import ar.com.nat.scoring.constantes.LogsConstantes;
import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.restconsuming.HttpRestConsuming;
import ar.com.nat.scoring.restconsuming.modelos.ConsultasBCURest;
import ar.com.nat.scoring.services.ServicesComunMethods;

public class BcuRest extends ServicesComunMethods  implements ConsultasRest{


	@Override
	public String Consultas(ConsultaOfertaRequest offerequest,ConsultaOfertaResponse offeresponse,HttpRestConsuming httpconsult,RestURLConsulting url) throws ExceptionFallaRestConsult {
		String resp = "";
		offerequest.setPaso(14104);
			for(ConsultasBCURest bcu : offeresponse.getBcu()) {
				if(bcu.getCRC_Vigente().compareTo(0) == 0) {
					try {
						resp =  httpconsult.GetHttp(formateoURLQuery(url.getURL_BCU(),bcu.getNumero(),bcu.getPeriodo(),bcu.getPais(),bcu.getTipo()));
						}catch (Exception e) {
							LogsConstantes.logRest.error("Error consulta BCU"+e.getMessage());
							throw new ExceptionFallaRestConsult("Falla en la consulta de datos");
						}
					}
			}
			return resp;
	}

}
