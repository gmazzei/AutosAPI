package main.java.repository;

import java.util.List;

import main.java.domain.Auto;

import org.springframework.data.repository.CrudRepository;


public interface AutoRepository extends CrudRepository<Auto, Integer> {
	
	public List<Auto> findAll();
	
}
