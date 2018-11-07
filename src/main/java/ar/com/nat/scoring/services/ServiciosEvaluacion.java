package ar.com.nat.scoring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.nat.scoring.constantes.ProceduresName;
import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.dao.ScoringDaoImp;
import ar.com.nat.scoring.dao.parametersquery.ParametersEvaluacion;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.exception.customexception.ExceptionNotFound;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.restconsuming.HttpRestConsuming;
import ar.com.nat.scoring.restconsuming.modelos.BPERestResponse;
import ar.com.nat.scoring.servicesimpl.EnumRests;
import ar.com.nat.scoring.servicesimpl.ConsultasRest;

@Service
public class ServiciosEvaluacion {

	@Autowired
	private ScoringDaoImp dao;
	
	@Autowired
	private HttpRestConsuming httpconsult;
	
	@Autowired
	private RestURLConsulting url;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	ConsultaOfertaResponse offeresponse = new ConsultaOfertaResponse();
	
	public ConsultaOfertaResponse BuscaOfertasDisponibles(ConsultaOfertaRequest oferta) throws Exception{
		offeresponse = (ConsultaOfertaResponse)this.dao.ExecStored(ProceduresName.PROC_14100, ParametersEvaluacion.class,ConsultaOfertaResponse.class,oferta);
		if(offeresponse.getConsulta() != null) {
			String consulta = offeresponse.getConsulta();
			for(int i=0; i < 3; i++) {
				if(offeresponse.getConsulta() != null) {
					String resp = ConsumingRests(offeresponse.getConsulta(), oferta, offeresponse);
					if(consulta.equals(offeresponse.getConsulta())){
						log.info("response del requerimiento HTTP:"+resp + "multiple consultas a "+consulta);
						throw new ExceptionNotFound("No hay ofertas disponibles");
					}else if(!resp.equals("201")){
						log.error("error consulta HTTP:"+resp);
						throw new ExceptionFallaRestConsult("Falla en la consulta de datos");
					}
				}
				else {
					break;
				}
			}
			if(offeresponse.getOffer() == null) {
				log.info("no hay ofertas diponibles");
				throw new ExceptionNotFound("No hay ofertas disponibles");
			}else {
				return offeresponse;
			}
		}else {
			if(offeresponse.getOffer() == null) {
				log.info("no hay ofertas diponibles");
				throw new ExceptionNotFound("No hay ofertas disponibles");
			}else {
				return offeresponse;
			}
		}
	}

	private String ConsumingRests(String consulta, ConsultaOfertaRequest offerequest,ConsultaOfertaResponse offeresponse) throws Exception {
		/**
		 * en el caso de que se agrande las consultas hay de convertir al designer pattern
		 */
		String  resp = "";
		consulta = consulta.toUpperCase();

		for(EnumRests rest : EnumRests.values()) {
			if(rest.name().equals(consulta)) {
				ConsultasRest ev = rest.getIntr();
				resp = ev.Consultas(offerequest, offeresponse, httpconsult,url);
				break;
			}
		}
		if(resp.contains("4")) {
			//body for error response //TODO
			return resp;
		}
		 BPERestResponse repjson  = this.httpconsult.JsonToEntity(resp, BPERestResponse.class);
			if(repjson.getCode() != 201) {
				return repjson.getMessage();
			}
		offeresponse = (ConsultaOfertaResponse)this.dao.ExecStored(ProceduresName.PROC_14100, ParametersEvaluacion.class,ConsultaOfertaResponse.class,offerequest);
		return repjson.getCode().toString();
	}

}
