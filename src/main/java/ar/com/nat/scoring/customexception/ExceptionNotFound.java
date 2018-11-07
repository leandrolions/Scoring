package ar.com.nat.scoring.customexception;

@SuppressWarnings("serial")
public class ExceptionNotFound extends Exception{

	public ExceptionNotFound(String message) {
		super(message);
	}
}
