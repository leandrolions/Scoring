package ar.com.nat.scoring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ar.com.nat.scoring.constantes.ProceduresName;
import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.dao.ScoringDaoImp;
import ar.com.nat.scoring.dao.parametersquery.ParametersVerficaSms;
import ar.com.nat.scoring.exception.customexception.ExceptionAnotherRestConsult;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.requests.VerificaSMSRequest;
import ar.com.nat.scoring.response.VerificaSMSResponse;
import ar.com.nat.scoring.restconsuming.HttpRestConsuming;
import ar.com.nat.scoring.restconsuming.modelos.EnviarSMSRest;
import ar.com.nat.scoring.restconsuming.modelos.EnviarSMSRestResponse;

@Service
public class ServiciosSms extends ServicesComunMethods{
	
	@Autowired
	private ScoringDaoImp dao;
	
	@Autowired
	private HttpRestConsuming httpconsult;
	
	@Autowired
	private RestURLConsulting url;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param VerificaSMSRequest class
	 * @return devuelve el retorno de REST SMS
	 * @throws ExceptionAnotherRestConsult sin parametros de retorno de stored procedure
	 * @throws ExceptionFallaRestConsult falla en requerimiento HTTP REST
	 */
	public VerificaSMSResponse ConsultaOEnviaSMS(VerificaSMSRequest sms) throws Exception {
		VerificaSMSResponse resp = this.dao.ExecStored(ProceduresName.PROC_17001, ParametersVerficaSms.class,VerificaSMSResponse.class,sms);
		if(resp.getSms() != null) {
			if(resp.getSms().getDestination() != null && resp.getSms().getMessage() != null) {
			EnviarSMSRestResponse response = ConsumingSMSRest(resp.getSms());
			resp.setMessage(response.getEnvioSmsResult());
			}
			else {
				throw new ExceptionAnotherRestConsult("No se ha podido enviar SMS en momento");
			}
		}
		return resp;
	}

	 private EnviarSMSRestResponse ConsumingSMSRest(EnviarSMSRest sms) throws ExceptionFallaRestConsult {
		 try {
//			 LinkedMultiValueMap<String, Object> params =  creamap(sms,EnviarSMSRest.class);
			 String response = this.httpconsult.PostHttp(this.url.getURL_SMS(),sms);
			 if(response == "") {
				 log.error("Falla en el servicio de envio de SMS no hubo respusta del servicio");
					throw new ExceptionFallaRestConsult("No se ha podido enviar SMS");
			 }
			 return setSMSResponse(response);

		} catch (HttpClientErrorException e) {
			log.error("Servicio http SMS devolvio"+e.getStatusCode()+"-"+e.getMessage());
			return setSMSResponse(e.getResponseBodyAsString());
		}
		 catch (Exception e2) {
			 log.error("Falla en el servicio de envio de SMS"+e2.getMessage());
			throw new ExceptionFallaRestConsult("No se ha podido enviar SMS en momento");
		 }
	 }

	 private EnviarSMSRestResponse setSMSResponse(String bodyJson) {
		 	Gson gson = new Gson();
			JsonObject obj = gson.fromJson(bodyJson, JsonObject.class);
			JsonElement element = obj.get("EnvioSmsResponse");
			EnviarSMSRestResponse message = gson.fromJson(element, EnviarSMSRestResponse.class);
			if(message.getEnvioSmsResult().contains("OK")) {
				message.setEnvioSmsResult("SMS Enviado con suceso");
			}else {
				message.setEnvioSmsResult("SMS no pudo se enviado");
			}
			return message;
	 }
}
