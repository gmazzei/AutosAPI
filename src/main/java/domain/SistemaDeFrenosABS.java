package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Sistema de Frenos ABS")
public class SistemaDeFrenosABS extends Opcional {
	
	public SistemaDeFrenosABS() {
		this.precio = 14000.0;
	}
	
}
