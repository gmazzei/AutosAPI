package main.java.validation;

public class TipoNoExisteException extends RuntimeException {
	
	public static final String CUSTOM_MESSAGE = "No existe variante con el nombre ";
	
	public TipoNoExisteException(String nombre) {
		super(CUSTOM_MESSAGE + nombre);
	}
	
	
	
}
