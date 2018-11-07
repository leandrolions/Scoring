package ar.com.nat.scoring.dao.parametersquery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import ar.com.nat.scoring.dao.ISetQueryParameters;
import ar.com.nat.scoring.dao.querymappers.Results;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.response.Offer;
import ar.com.nat.scoring.restconsuming.modelos.ConsultasBCURest;

public class ParametersEvaluacion  implements ISetQueryParameters{

	@Override
	public StoredProcedureQuery setParameters(StoredProcedureQuery query, Object... objects) {

		
		ConsultaOfertaRequest consul= new ConsultaOfertaRequest();
		if(objects[0] instanceof ConsultaOfertaRequest) {
			consul = (ConsultaOfertaRequest)objects[0];
		}
		else {
			throw new RuntimeException();
		}
		query.registerStoredProcedureParameter("ERR_ErrorTipo",short.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorNro",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("ERR_ErrorMsg",String.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("PER_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Monto",Float.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Cuota",Float.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("session_id",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("paso",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_CNL_COD",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("Sol_Nro_Procesar",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("Per_Ban_Nro",Integer.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("SOL_Nro_Out",Integer.class,ParameterMode.OUT);
		query.registerStoredProcedureParameter("consulta",String.class,ParameterMode.OUT);
		
		query.setParameter("PER_Nro", consul.getId());
		query.setParameter("SOL_CNL_COD", 1);
		query.setParameter("Sol_Nro_Procesar", consul.getSol_nro_procesar());
		query.setParameter("Per_Ban_Nro", consul.getPer_ban_nro());
		query.setParameter("paso", consul.getPaso());
		query.setParameter("SOL_Monto", consul.getAmount().floatValue());
		query.setParameter("SOL_Cuota", consul.getFee().floatValue());
		query.setParameter("session_id", consul.getSession_id());
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameters(StoredProcedureQuery query) {
		ConsultaOfertaResponse offer = new ConsultaOfertaResponse();
		
		offer.setRequest_id((Integer) query.getOutputParameterValue("SOL_Nro_Out"));
		Short Error = (short) query.getOutputParameterValue("ERR_ErrorTipo");
		String Consulta = (String) query.getOutputParameterValue("consulta");
			if(Error == 1) {
				offer.setMessage((String)query.getOutputParameterValue("ERR_ErrorMsg"));
			}
			else {
				if(Consulta.equalsIgnoreCase("BCU")) {
					List<ConsultasBCURest> bcu = new ArrayList<ConsultasBCURest>();
					bcu = Results.map(query.getResultList(),ConsultasBCURest.class);
						if(bcu != null) {			
							offer.setBcu(bcu);
							offer.setConsulta(Consulta);
						}
				}
				else if(Consulta.equalsIgnoreCase("EXPERTO") || Consulta.equalsIgnoreCase("PREFILTRO")) {
					Object cedula = query.getSingleResult();
					offer.setCedula(Integer.valueOf(cedula.toString()));
					offer.setConsulta(Consulta);
				}
				else if(Consulta.equalsIgnoreCase("simulabantotal")) {
					List<Offer> ofertas = new ArrayList<Offer>();
					ofertas = Results.map(query.getResultList(),Offer.class);
					if(ofertas != null) {
						offer.setOffer(ofertas);
					}
				}
				else {
					List<Offer> ofertas = new ArrayList<Offer>();
						ofertas = Results.map(query.getResultList(),Offer.class);
						if(ofertas != null) {
							offer.setOffer(ofertas);
						}
				}
			}
		return (T) offer;
	}

}
