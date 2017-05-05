package main.java.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Techo Corredizo")
public class TechoCorredizo extends Opcional {
	
	public TechoCorredizo() {
		this.precio = 12000.0;
	}
	
}
