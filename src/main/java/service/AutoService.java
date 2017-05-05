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
	
	public Auto findOne(Integer id) {
		return autoRepository.findOne(id);
	}
	
	public Auto save(Auto auto) {
		return autoRepository.save(auto);
	}
	
	public Auto update(Auto auto) {
		return autoRepository.save(auto);
	}
	
	public void delete(Integer id) {
		autoRepository.delete(id);
	}
}
