package main.java.utils;

import main.java.domain.Airbag;
import main.java.domain.AireAcondicionado;
import main.java.domain.Auto;
import main.java.domain.Coupe;
import main.java.domain.Familiar;
import main.java.domain.LlantasDeAleacion;
import main.java.domain.Sedan;
import main.java.domain.SistemaDeFrenosABS;
import main.java.domain.TechoCorredizo;

public class AutoUtils {
	
	public static Auto crearAuto(String nombre, String opcionales) {
		
		Auto auto;
		
		if (nombre.equalsIgnoreCase("sedan")) {
			auto = new Sedan();
		} else if (nombre.equalsIgnoreCase("familiar")) {
			auto = new Familiar();
		} else {
			auto = new Coupe();
		}
		
		
		for (String opcional : opcionales.split(",")) {
			if (opcional.equalsIgnoreCase("techo_corredizo")) {
				auto.agregarOpcional(new TechoCorredizo());
			} else if (opcional.equalsIgnoreCase("aire_acondicionado")) {
				auto.agregarOpcional(new AireAcondicionado());
			} else if (opcional.equalsIgnoreCase("sistema_de_frenos_abs")) {
				auto.agregarOpcional(new SistemaDeFrenosABS());
			} else if (opcional.equalsIgnoreCase("airbag")) {
				auto.agregarOpcional(new Airbag());
			} else if (opcional.equalsIgnoreCase("llantas_de_aleacion")) {
				auto.agregarOpcional(new LlantasDeAleacion());
			}
		}
		
		
		return auto;
	}
	
}
