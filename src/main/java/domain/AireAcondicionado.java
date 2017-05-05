package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Aire Acondicionado")
public class AireAcondicionado extends Opcional {
	
	public AireAcondicionado() {
		this.precio = 20000.0;
	}
	
}
