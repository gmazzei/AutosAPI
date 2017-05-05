package main.java.service;

import java.util.List;

import main.java.domain.Tipo;
import main.java.repository.TipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoService {
	
	@Autowired
	private TipoRepository tipoRepository;
	
	public List<Tipo> findAll() {
		return tipoRepository.findAll(); 
	}
	
	public Tipo findOne(Integer id) {
		return tipoRepository.findOne(id);
	}
	
	public Tipo findBy(String nombre) {
		return tipoRepository.findByNombre(nombre);
	}

}
