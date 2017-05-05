package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Coupe")
public class Coupe extends Auto {
	
	public Coupe() {
		super();
		this.nombre = "Coupe";
		this.precio = 270000.0;
	}

}
