package ar.com.nat.scoring.restconsuming;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ar.com.nat.scoring.constantes.RestURLConsulting;
import ar.com.nat.scoring.exception.customexception.ExceptionFallaRestConsult;

@Service
public class HttpRestConsuming {
	
	private HttpComponentsClientHttpRequestFactory requestFactory;

	private RestTemplate restTemplate;
	@Autowired
	RestURLConsulting Params;
	

	public String GetHttp(String URL) throws Exception {
		setSSLandRestT(true);
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
			return response.getBody();
		}catch(HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}catch (Exception e) {
			throw new ExceptionFallaRestConsult(e.getMessage());
		}
		
	}
	
//	public String PostConsult(String URL,LinkedMultiValueMap<String, Object> Parameters) throws Exception{
	public <T> String PostHttp(String URL, T Parameters) throws Exception{

		setSSLandRestT(true);
		HttpHeaders header = setHeadersParams(Params.getSMS_USER(),Params.getSMS_PASSWORD());
		HttpEntity<T> request = new HttpEntity<T>((T) Parameters, header);
		try {
			ResponseEntity<String> map =  restTemplate.exchange(URL,HttpMethod.POST, request, String.class);
			 return map.getBody();
		}catch (HttpClientErrorException e) {
			return e.getResponseBodyAsString();
		}catch (Exception e) {
			throw new ExceptionFallaRestConsult(e.getMessage());
		}

	}
	protected void setSSLandRestT(boolean activo) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		if(activo) {
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			RequestConfig customizedRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();

			CloseableHttpClient httpClient = HttpClients.custom()
			        .setSSLSocketFactory(csf).setDefaultRequestConfig(customizedRequestConfig)
			        .build();
			 this.requestFactory = new HttpComponentsClientHttpRequestFactory();
			 this.requestFactory.setHttpClient(httpClient);

			 this.restTemplate = new RestTemplate(this.requestFactory);
		}
		else {
			this.restTemplate = new RestTemplate();
		}
	}
	
	protected HttpHeaders setHeadersParams(String User,String Password) {
        //Set the headers you need send
        
        String auth = User + ":" + Password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set( "Authorization", authHeader );
        headers.set("cache-control", "no-cache");
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Create a new HttpEntity
       return  headers;
	}
	
	public <T> T JsonToEntity(String Json, Class<T> classe) {
		return new Gson().fromJson(Json,classe);
	}
}
