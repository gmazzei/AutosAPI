package main.java.repository;

import java.util.List;

import main.java.domain.Tipo;

import org.springframework.data.repository.CrudRepository;


public interface TipoRepository extends CrudRepository<Tipo, Integer> {
	
	public List<Tipo> findAll();
	
	public Tipo findOne(Integer id);
	
	public Tipo findByNombre(String nombre);
	
}
