	package ar.com.nat.scoring.restconsuming.modelos;

import java.math.BigInteger;

import ar.com.nat.scoring.dao.querymappers.NativeQueryResultColumn;
import ar.com.nat.scoring.dao.querymappers.NativeQueryResultEntity;

@NativeQueryResultEntity
public class ConsultasBCURest {
	
	 @NativeQueryResultColumn(index=0)
	BigInteger numero;
	 @NativeQueryResultColumn(index=1)
	BigInteger periodo;
	 @NativeQueryResultColumn(index=2)
	Integer CRC_Vigente;
	 @NativeQueryResultColumn(index=3)
	String pais;
	 @NativeQueryResultColumn(index=4)
	String tipo;
	 
	public BigInteger getNumero() {
		return numero;
	}
	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}
	public BigInteger getPeriodo() {
		return periodo;
	}
	public void setPeriodo(BigInteger periodo) {
		this.periodo = periodo;
	}
	public Integer getCRC_Vigente() {
		return CRC_Vigente;
	}
	public void setCRC_Vigente(Integer cRC_Vigente) {
		CRC_Vigente = cRC_Vigente;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	 
}
