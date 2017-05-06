package main.java.validation;

public class AutoNoExisteException extends RuntimeException {
	
	public static final String CUSTOM_MESSAGE = "No existe auto con el id ";
	
	public AutoNoExisteException(Integer id) {
		super(CUSTOM_MESSAGE + id);
	}
	
	
	
}
