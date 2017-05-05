package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Sedan")
public class Sedan extends Auto {
	
	public Sedan() {
		super();
		this.nombre = "Sedan";
		this.precio = 230000.0;
	}

}
