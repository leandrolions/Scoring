package ar.com.nat.scoring.parametersquery;

import java.math.BigInteger;

public class TypesMethods {
	
	public Integer setInteger(Object obj) {
		try {
		return Integer.parseInt(String.valueOf(obj));
		}catch (Exception e) {
			return 0;
		}
	}
	
	public String setString(Object obj) {
		try {
		return String.valueOf(obj);
		}catch (Exception e) {
			return "0";
		}
	}
	
	public BigInteger setBigInteger(Object obj) {
		try {
		return new BigInteger(String.valueOf(obj));
		}catch (Exception e) {
			return BigInteger.ZERO;
		}
	}
	
	public Float setFloat(Object obj) {
		try {
		return Float.valueOf(String.valueOf(obj));
		}catch (Exception e) {
			return Float.valueOf("0.0");
		}
	}
}
