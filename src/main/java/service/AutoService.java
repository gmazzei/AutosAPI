package main.java.service;

import java.util.List;

import main.java.domain.Auto;
import main.java.repository.AutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {
	
	@Autowired
	private AutoRepository autoRepository;
	
	public List<Auto> findAll() {
		return autoRepository.findAll(); 
	}
	
	public Auto save(Auto auto) {
		return auto;
	}
	
	public Auto update(Auto auto) {
		return auto;
	}
	
	public void delete(Auto auto) {
		
	}
}
