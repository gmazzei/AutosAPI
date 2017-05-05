package main.java.utils;

import main.java.domain.Auto;
import main.java.domain.Tipo;


public class AutoUtils {
	
	public static Auto crearAuto(Tipo tipo, String opcionales) {
		Auto auto = new Auto();
		auto.setTipo(tipo);
		return auto;
	}
	
}
