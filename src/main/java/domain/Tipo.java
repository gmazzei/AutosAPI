package main.java.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos")
public class Tipo {
	
	@Id
	private Integer id;
	
	@Column(unique = true)
	private String nombre;
	
	@Column
	private Double precio;
	
		
	
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
		return precio;
	}	
	
	
}
