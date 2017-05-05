package main.java.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo")
@Table(name = "autos")
public abstract class Auto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column
	protected String nombre;
	
	@Column
	protected Double precio;
	
	@Transient
	protected List<Opcional> opcionales; 
	
	public Auto() {
		this.opcionales = new ArrayList<Opcional>();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Double getPrecio() {
		Double precioTotal = this.precio;
		
		for (Opcional unOpcional : opcionales) {
			precioTotal += unOpcional.getPrecio();
		}
		
		return precioTotal;
	}


	public List<Opcional> getOpcionales() {
		return opcionales;
	}


	public void setOpcionales(List<Opcional> opcionales) {
		this.opcionales = opcionales;
	}
	
	
	
}
