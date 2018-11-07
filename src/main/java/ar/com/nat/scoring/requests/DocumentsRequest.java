package ar.com.nat.scoring.requests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.nat.scoring.entities.DocumentosRequeridos;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Documentos")
public class DocumentsRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Id de Persona",required=true)
	Integer id;
	@ApiModelProperty(notes = "Nro Solicitud",required=true)
	Integer request_id;
	@ApiModelProperty(notes = "Lista de Documentos",required=true)
	List<ImageDocument> documents;
	@ApiModelProperty(notes = "Id de Session",required=true)
	String  session_id;
	@JsonIgnore
	List<DocumentosRequeridos> docs;
	
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
	public List<ImageDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(List<ImageDocument> documents) {
		this.documents = documents;
		ArmaDocumentos();
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	@PostConstruct
	public void ArmaDocumentos() {
		docs = new ArrayList<>();
		DocumentosRequeridos temp_docs = new DocumentosRequeridos();
		for(ImageDocument img : documents) {
			temp_docs.setCode(img.getCode());
			temp_docs.setUrl(img.getUrl());
			temp_docs.setPer_nro(this.id);
			temp_docs.setSol_nro(this.request_id);
			docs.add(temp_docs);
		}
	}
	public List<DocumentosRequeridos> getDocs() {
		return docs;
	}
	public void setDocs(List<DocumentosRequeridos> docs) {
		this.docs = docs;
	}
}
