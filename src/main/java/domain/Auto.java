package main.java.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "autos")
public class Auto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auto_opcional", joinColumns = @JoinColumn(name = "auto_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "opcional_id", referencedColumnName = "id"))
	private List<Opcional> opcionales; 
	
	public Auto() {
		
	}
	
	public Auto(Tipo tipo, List<Opcional> opcionales) {
		this.tipo = tipo;
		this.opcionales = opcionales;
	}

	
	
	
	public void agregarOpcional(Opcional opcional) {
		opcionales.add(opcional);
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

		
	public Double getPrecio() {
		Double precioTotal = this.tipo.getPrecio();
		
		for (Opcional unOpcional : opcionales) {
			precioTotal += unOpcional.getPrecio();
		}
		
		return precioTotal;
	}
	
	

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Opcional> getOpcionales() {
		return opcionales;
	}


	public void setOpcionales(List<Opcional> opcionales) {
		this.opcionales = opcionales;
	}
	
}
