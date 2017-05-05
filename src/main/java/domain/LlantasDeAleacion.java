package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Llantas de Aleacion")
public class LlantasDeAleacion extends Opcional {
	
	public LlantasDeAleacion() {
		this.precio = 12000.0;
	}
	
}
