package ar.com.nat.scoring.response;

import java.io.Serializable;
import java.util.List;

public class SelectOfertaResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Integer id;
	Integer request_id;
	List<Document> documents;
	String message;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequest_id() {
		return request_id;
	}
	public void setRequest_id(Integer request_id) {
		this.request_id = request_id;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
