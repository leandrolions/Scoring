package ar.com.nat.scoring.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.nat.scoring.constantes.ProceduresName;
import ar.com.nat.scoring.dao.ScoringDao;
import ar.com.nat.scoring.dao.parametersquery.ParametersAcceptTyC;
import ar.com.nat.scoring.dao.parametersquery.ParametersDocumentos;
import ar.com.nat.scoring.dao.parametersquery.ParametersRangoMonto;
import ar.com.nat.scoring.dao.parametersquery.ParametersSelectOferta;
import ar.com.nat.scoring.exception.customexception.ExceptionAnotherRestConsult;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;
import ar.com.nat.scoring.exception.customexception.ExceptionNotFound;
import ar.com.nat.scoring.requests.AcceptTCRequest;
import ar.com.nat.scoring.requests.ConsultaOfertaRequest;
import ar.com.nat.scoring.requests.DocumentsRequest;
import ar.com.nat.scoring.requests.OfferMontosRequest;
import ar.com.nat.scoring.requests.SelectOfertaRequest;
import ar.com.nat.scoring.requests.VerificaSMSRequest;
import ar.com.nat.scoring.response.AcceptTCResponse;
import ar.com.nat.scoring.response.ConsultaOfertaResponse;
import ar.com.nat.scoring.response.DocumentResponse;
import ar.com.nat.scoring.response.ErrorResponse;
import ar.com.nat.scoring.response.OfferMontosResponse;
import ar.com.nat.scoring.response.SelectOfertaResponse;
import ar.com.nat.scoring.response.VerificaSMSResponse;
import ar.com.nat.scoring.services.ServiciosEvaluacion;
import ar.com.nat.scoring.services.ServiciosSms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Scoring, evaluacion y provedor de servicios",produces="application/json")
@RestController
public class ScoringController {
	
	@Autowired
	ScoringDao dao;
	@Autowired
	ServiciosSms services;
	@Autowired
	ServiciosEvaluacion evaluacion;
	
	 private final Logger logger = LoggerFactory.getLogger("controlers");
	
	@PostMapping(path="/checksms",produces="application/json")
	@ApiOperation(value="Verificar/ Enviar  SMS",response=VerificaSMSRequest.class,notes="Verificar/ enviar SMS ",produces="application/json")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso ",response=VerificaSMSResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado SMS para los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	@CrossOrigin
	public ResponseEntity<Object> EnviarSms(@ApiParam(value = "Datos para la Consulta", required = true) @RequestBody VerificaSMSRequest sms,BindingResult result){
		VerificaSMSResponse respuestasms = new VerificaSMSResponse();
		ErrorResponse error = new ErrorResponse();
		if(result.hasErrors()) {
			String ListError = "";
			for(ObjectError obe : result.getAllErrors()) {
				ListError += obe.getDefaultMessage();
			}
			
			error.setMessage(ListError);
			return ResponseEntity.badRequest().body(error);
			
		}try {
			respuestasms = services.ConsultaOEnviaSMS(sms);
//			respuestasms = dao.ExecStored(ProceduresName.PROC_17001, ParametersVerficaSms.class,VerificaSMSResponse.class,sms);
			respuestasms.setId(sms.getId());
			respuestasms.setRequest_id(sms.getRequest_id());
		}catch (ExceptionAnotherRestConsult e) {
			logger.error("falla request HTTP SMS "+e.getMessage());
			error.setCode(1004);
			error.setMessage(e.getMessage());
			 return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
		} catch (Exception e) {
			logger.error("Error in data base: {}",e.getMessage());
			error.setCode(500);
			error.setMessage("Falla Interna del Servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			
		}

		return ResponseEntity.ok().body(respuestasms);
		
	}
	
	@PostMapping(path="/range",produces="application/json")
	@CrossOrigin
	@ApiOperation(value="Solicitar Rango de Monto Disponible",response=OfferMontosRequest.class,
	notes="Verificar rango de monto disponible para solicitud")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso",response=OfferMontosResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado montos disponibles",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	public ResponseEntity<Object> getMontosDisponibles(@ApiParam(value = "Datos para consulta de Rango de Montos disponibles", required = true) @RequestBody @Validated OfferMontosRequest oferta,BindingResult result){
		OfferMontosResponse montos = new OfferMontosResponse();
		ErrorResponse error = new ErrorResponse();
		if(result.hasErrors()) {
			logger.error("Fields Errors"+result.getFieldError());
			String ListError = "";
			for(ObjectError obe : result.getAllErrors()) {
				ListError += obe.getDefaultMessage();
			}
			error.setMessage(ListError);
			return ResponseEntity.badRequest().body(error);
		}
		
		try {
			montos = dao.ExecStored(ProceduresName.PROC_14001, ParametersRangoMonto.class,OfferMontosResponse.class,oferta);
			montos.setId(oferta.getId());
		}catch (ExceptionNotFound e) {
			logger.error("not foud exception "+e.getMessage());
			error.setCode(1004);
			error.setMessage(e.getMessage());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		} catch (Exception e) {
			logger.error("Error in data base:"+e.getMessage());
			error.setCode(500);
			error.setMessage("Falla Interna del Servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

		}
		return ResponseEntity.ok().body(montos);
	}
	
	
	@PostMapping(path="/request",produces="application/json")
	@ApiOperation(value="Consultar ofertas disponibles",response=ConsultaOfertaRequest.class,notes="Devuelve ofertas disponibles para elección",produces="application/json")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso",response=ConsultaOfertaResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado montos disponibles",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	public ResponseEntity<Object> getOfertasDisponibles(@RequestBody ConsultaOfertaRequest oferta,BindingResult result){
		ConsultaOfertaResponse ofertas = new ConsultaOfertaResponse();
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
			oferta.setPaso(0);
			oferta.setPer_ban_nro(0);
			oferta.setSol_nro_procesar(0);
			ofertas = evaluacion.BuscaOfertasDisponibles(oferta);
			ofertas.setId(oferta.getId());
			ofertas.setRequest_id(ofertas.getRequest_id());
		} catch (ExceptionNotFound e) {
			logger.error("not foud exception "+e.getMessage());
			error.setCode(1004);
			error.setMessage(e.getMessage());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}catch(ExceptionFallaRestConsult e) {
			logger.error("Falla en consulta HTTP"+e.getMessage());
			error.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			error.setMessage("Falla en la consulta de datos");
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(error);
		}catch (Exception e) {
			logger.error("Error in data base:"+e.getMessage());
			error.setCode(500);
			error.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		
		return ResponseEntity.ok().body(ofertas);
		
	}
	
	@PostMapping(path="/select",produces="application/json")
	@ApiOperation(value="Guardar Oferta Elegido",response=SelectOfertaRequest.class,notes="Guardar Oferta elegido",produces="application/json")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso",response=SelectOfertaResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado montos disponibles",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	public ResponseEntity<Object> setOfertaElegida(@RequestBody SelectOfertaRequest oferta,BindingResult result){
		SelectOfertaResponse ofertas = new SelectOfertaResponse();
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
			ofertas = dao.ExecStored(ProceduresName.PROC_15001, ParametersSelectOferta.class,SelectOfertaResponse.class,oferta);
			ofertas.setId(oferta.getId());
			ofertas.setRequest_id(oferta.getRequest_id());
		}catch (ExceptionNotFound e) {
			logger.error("not foud exception "+e.getMessage());
			error.setCode(1004);
			error.setMessage(e.getMessage());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		} catch (Exception e) {
			logger.error("Error in data base:"+e.getMessage());
			error.setCode(500);
			error.setMessage("Falla Interna del Servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		
		return ResponseEntity.ok().body(ofertas);
		
	}
	
	@PutMapping(path="/documents",produces="application/json")
	@ApiOperation(value="Guardar documentos",response=DocumentsRequest.class,notes="Guardar documentos",produces="application/json")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso",response=DocumentResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado montos disponibles",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	public ResponseEntity<Object> GuardarDocumentos(@RequestBody DocumentsRequest documentos,BindingResult result){
		DocumentResponse documento = new DocumentResponse();
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
			documento = dao.ExecStored(ProceduresName.PROC_17002, ParametersDocumentos.class,DocumentResponse.class,documentos);
			documento.setId(documentos.getId());
			documento.setRequest_id(documentos.getRequest_id());
		} catch (ExceptionNotFound e) {
			logger.error("not foud exception "+e.getMessage());
			error.setCode(1004);
			error.setMessage(e.getMessage());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}catch (Exception e) {
			logger.error("Error in data base:"+e.getMessage());
			error.setCode(500);
			error.setMessage("Falla Interna del Servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		
		return ResponseEntity.ok().body(documento);
		
	}
	@PostMapping(path="/accepttc",produces="application/json")
	@ApiOperation(value="",response=AcceptTCRequest.class,notes="Terminos y condiciones",produces="application/json")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Suceso",response=AcceptTCResponse.class),
	@ApiResponse(code=400,message="No permitido por los datos ingresados",response=ErrorResponse.class),
	@ApiResponse(code=404,message="No encontrado montos disponibles",response=ErrorResponse.class),
	@ApiResponse(code=417,message="Error de base de datos",response=ErrorResponse.class),
	@ApiResponse(code=403,message="acceso no permitido",response=ErrorResponse.class)})
	public ResponseEntity<Object> AceptarTYC(@RequestBody AcceptTCRequest accept,BindingResult result){
		AcceptTCResponse acceptresp = new AcceptTCResponse();
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
			acceptresp = dao.ExecStored(ProceduresName.PROC_18001, ParametersAcceptTyC.class,AcceptTCResponse.class,accept);
			acceptresp.setId(accept.getId());
			acceptresp.setRequest_id(accept.getRequest_id());
		} catch (ExceptionNotFound e) {
			logger.error("not foud exception "+e.getMessage());
			error.setCode(1004);
			error.setMessage(e.getMessage());
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}catch (Exception e) {
			logger.error("Error in data base:"+e.getMessage());
			error.setCode(500);
			error.setMessage("Falla Interna del Servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		
		return ResponseEntity.ok().body(acceptresp);
		
	}
}
