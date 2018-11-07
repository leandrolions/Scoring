package ar.com.nat.scoring.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import ar.com.nat.scoring.dao.querymappers.NativeQueryResultColumn;
import ar.com.nat.scoring.dao.querymappers.NativeQueryResultEntity;

@Entity
@NativeQueryResultEntity
public class Document implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="DOC_Code")
	@NativeQueryResultColumn(index=0)
	Integer code;
	@Column(name="DOC_Name")
	@NativeQueryResultColumn(index=1)
	String name;
	@Column(name="DOC_Text")
	@NativeQueryResultColumn(index=2)
	String text;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


}
