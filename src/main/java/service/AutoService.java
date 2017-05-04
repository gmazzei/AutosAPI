package main.java.service;

import java.util.ArrayList;
import java.util.List;

import main.java.domain.Auto;
import main.java.domain.Coupe;
import main.java.domain.Familiar;
import main.java.domain.SistemaDeFrenosABS;
import main.java.domain.TechoCorredizo;

public class AutoService {
	
	public List<Auto> findAll() {
		List<Auto> list = new ArrayList<Auto>();
		
		Auto coupe = new Coupe();
		coupe.setId(1);
		coupe.agregarOpcional(new TechoCorredizo());
		coupe.agregarOpcional(new SistemaDeFrenosABS());
		list.add(coupe);
		
		Auto familiar = new Familiar();
		familiar.setId(2);
		list.add(familiar);
		
		return list; 
	}
	
}
