package ar.com.nat.scoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.nat.scoring.constantes.ProceduresName;
import ar.com.nat.scoring.daointerf.ScoringDao;
import ar.com.nat.scoring.parametersquery.ParameterRangoMonto;
import ar.com.nat.scoring.parametersquery.ParametersVerficaSms;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.requests.VerificaSMSRequest;
import ar.com.nat.scoring.response.ErrorResponse;
import ar.com.nat.scoring.response.OfferMontosResponse;
import ar.com.nat.scoring.response.VerificaSMSResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Scoring, evaluacion y provedor de servicios",tags={})
@RestController
public class ScoringController {
	
	@Autowired
	ScoringDao dao;
	
	@PostMapping("/checksms")
	@ApiOperation(value="Verificar envio SMS",response=VerificaSMSRequest.class,notes="Verificar el envio de SMS luego de enviar SMS")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso ",response=VerificaSMSResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado SMS para los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	@CrossOrigin
	public ResponseEntity<Object> EnviarSms(@ApiParam(value = "Datos para la Consulta", required = true) @RequestBody VerificaSMSRequest sms,BindingResult result){
		VerificaSMSResponse response = new VerificaSMSResponse();
		ErrorResponse error = new ErrorResponse();
		if(result.hasErrors()) {
			String ListError = "";
			for(ObjectError obe : result.getAllErrors()) {
				ListError += obe.getDefaultMessage();
			}
			
			error.setMessage(ListError);
			return ResponseEntity.badRequest().body(error);
			
		}try {
		response = dao.ExecStored(ProceduresName.PROC_17001, ParametersVerficaSms.class,VerificaSMSResponse.class,sms);
		if(response == null) {
			error.setCode(1004);
			error.setMessage("Not Found SMS for data");
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		} catch (Exception e) {
			error.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
			
		}
		return ResponseEntity.ok().body(response);
		
	}
	
	@PostMapping("/range")
	@CrossOrigin
	@ApiOperation(value="Solicitar Rango de Monto Disponible",response=ConsultaOfertaRequest.class,notes="Verificar rango de monto disponible para solicitud")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso",response=OfferMontosResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado montos disponibles",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	public ResponseEntity<Object> getMontosDisponibles(@ApiParam(value = "Datos para consulta de Rango de Montos disponibles", required = true) @RequestBody ConsultaOfertaRequest oferta,BindingResult result){
		OfferMontosResponse montos = new OfferMontosResponse();
		ErrorResponse error = new ErrorResponse();
		if(result.hasErrors()) {
			String ListError = "";
			for(ObjectError obe : result.getAllErrors()) {
				ListError += obe.getDefaultMessage();
			}
			error.setMessage(ListError);
			return ResponseEntity.badRequest().body(error);
		}
		
		try {
			montos = dao.ExecStored(ProceduresName.PROC_14001, ParameterRangoMonto.class,OfferMontosResponse.class,oferta);
			if(montos == null) {
				error.setMessage("Not Found Montos");
				error.setCode(1004);
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
			}
		} catch (Exception e) {
			error.setMessage(e.getMessage());			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);

		}
		return ResponseEntity.ok().body(montos);
	}
}
