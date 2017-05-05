package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Airbag")
public class Airbag extends Opcional {
	
	public Airbag() {
		this.precio = 7000.0;
	}
	
}
