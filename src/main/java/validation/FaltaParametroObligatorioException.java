package main.java.validation;

public class FaltaParametroObligatorioException extends RuntimeException {
	
	public static final String CUSTOM_MESSAGE = "Falta el parametro ";
	
	public FaltaParametroObligatorioException(String parametro) {
		super(CUSTOM_MESSAGE + parametro);
	}
	
	
	
}
