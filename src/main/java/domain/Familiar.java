package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Familiar")
public class Familiar extends Auto {
	
	public Familiar() {
		super();
		this.nombre = "Familiar";
		this.precio = 245000.0;
	}

}
