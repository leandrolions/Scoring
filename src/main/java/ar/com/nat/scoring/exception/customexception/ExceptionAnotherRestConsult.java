package ar.com.nat.scoring.exception.customexception;

@SuppressWarnings("serial")
public class ExceptionAnotherRestConsult extends Exception{

	public ExceptionAnotherRestConsult(String message,Throwable cause) {
		super(message,cause);
	}
	public ExceptionAnotherRestConsult(String message) {
		super(message);
	}
}
