package ar.com.nat.scoring.constantes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestURLConsulting {
	
	@Value("${url.bcu}")
	String URL_BCU;
	@Value("${url.prefiltro}")
	String URL_PREFILTRO;
	@Value("${url.experto}")
	String URL_EXPERTO;
	@Value("${url.sms}")
	String URL_SMS;
	String URL_CREAR;
	String URL_SIMULAR;
	@Value("${sms.user}")
	String SMS_USER;
	@Value("${sms.password}")
	String SMS_PASSWORD;
	
	
	public String getURL_BCU() {
		return URL_BCU;
	}
	
	public void setURL_BCU(String uRL_BCU) {
		URL_BCU = uRL_BCU;
	}
	public String getURL_PREFILTRO() {
		return URL_PREFILTRO;
	}
	public void setURL_PREFILTRO(String uRL_PREFILTRO) {
		URL_PREFILTRO = uRL_PREFILTRO;
	}
	public String getURL_EXPERTO() {
		return URL_EXPERTO;
	}
	public void setURL_EXPERTO(String uRL_EXPERTO) {
		URL_EXPERTO = uRL_EXPERTO;
	}

	public String getURL_SMS() {
		return URL_SMS;
	}

	public void setURL_SMS(String uRL_SMS) {
		URL_SMS = uRL_SMS;
	}

	public String getSMS_USER() {
		return SMS_USER;
	}

	public void setSMS_USER(String sMS_USER) {
		SMS_USER = sMS_USER;
	}

	public String getSMS_PASSWORD() {
		return SMS_PASSWORD;
	}

	public void setSMS_PASSWORD(String sMS_PASSWORD) {
		SMS_PASSWORD = sMS_PASSWORD;
	}

	public String getURL_CREAR() {
		return URL_CREAR;
	}

	public void setURL_CREAR(String uRL_CREAR) {
		URL_CREAR = uRL_CREAR;
	}

	public String getURL_SIMULAR() {
		return URL_SIMULAR;
	}

	public void setURL_SIMULAR(String uRL_SIMULAR) {
		URL_SIMULAR = uRL_SIMULAR;
	}
	
	

}
