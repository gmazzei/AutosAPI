package main.java.service;

import java.util.List;

import main.java.domain.Opcional;
import main.java.repository.OpcionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpcionalService {
	
	@Autowired
	private OpcionalRepository opcionalRepository;
	
	public List<Opcional> findAll() {
		return opcionalRepository.findAll(); 
	}
	
	public List<Opcional> findBySiglaIn(List<String> siglas) {
		return opcionalRepository.findBySiglaIn(siglas);
	}
}
