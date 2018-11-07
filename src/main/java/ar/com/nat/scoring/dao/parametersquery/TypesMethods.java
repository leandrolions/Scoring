package ar.com.nat.scoring.dao.parametersquery;

import java.math.BigInteger;

public class TypesMethods {
	
	@Deprecated
	public static Integer setInteger(Object obj) {
		try {
		return Integer.parseInt(String.valueOf(obj));
		}catch (Exception e) {
			return 0;
		}
	}
	public static String setString(Object obj) {
		try {
		return String.valueOf(obj);
		}catch (Exception e) {
			return "0";
		}
	}
	@Deprecated
	public static BigInteger setBigInteger(Object obj) {
		try {
		return new BigInteger(String.valueOf(obj));
		}catch (Exception e) {
			return BigInteger.ZERO;
		}
	}
	@Deprecated
	public static Float setFloat(Object obj) {
		try {
		return Float.valueOf(String.valueOf(obj));
		}catch (Exception e) {
			return Float.valueOf("0.0");
		}
	}
}
