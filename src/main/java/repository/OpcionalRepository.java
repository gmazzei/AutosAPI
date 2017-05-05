package main.java.repository;

import java.util.List;

import main.java.domain.Opcional;

import org.springframework.data.repository.CrudRepository;


public interface OpcionalRepository extends CrudRepository<Opcional, Integer> {
	
	public List<Opcional> findAll();
	
	public List<Opcional> findBySiglaIn(List<String> siglas);
	
}
