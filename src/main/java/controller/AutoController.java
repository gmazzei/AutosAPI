package main.java.controller;

import java.util.Arrays;
import java.util.List;

import main.java.domain.Auto;
import main.java.domain.Opcional;
import main.java.domain.Tipo;
import main.java.json.AutoSerializer;
import main.java.service.AutoService;
import main.java.service.OpcionalService;
import main.java.service.TipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class AutoController {
	
	@Autowired
	private AutoService autoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	@Autowired
	private TipoService tipoService;
	
	private final Gson gson = new GsonBuilder().registerTypeAdapter(Auto.class, new AutoSerializer()).create();
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<String> getAutos() {
		List<Auto> autos = autoService.findAll();
		return ResponseEntity.ok(gson.toJson(autos));
    }
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.GET)
	@ResponseBody
    public Auto getOneAutos(@PathVariable("id") Integer id) {
		return autoService.findOne(id);
    }
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.POST)
	@ResponseBody
    public ResponseEntity<String> postAutos(@RequestParam(value = "nombre", required = true) String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		Tipo tipo = tipoService.findBy(nombre);
		
		if (tipo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{code: 400, message: 'No hay variantes con el nombre " + nombre + "'}");
		}
		
		List<String> siglas = Arrays.asList(opcionales.split(","));
		List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
		
		Auto auto = new Auto(tipo, listaDeOpcionales);
		auto = autoService.save(auto);
		return ResponseEntity.ok(gson.toJson(auto));
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public ResponseEntity<String> putAutos(@PathVariable("id") Integer id, @RequestParam(value = "nombre", required = true) String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		Tipo tipo = tipoService.findBy(nombre);
		
		if (tipo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{code: 400, message: 'No hay variantes con el nombre " + nombre + "'}");
		}
		
		List<String> siglas = Arrays.asList(opcionales.split(","));
		List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
		
		Auto auto = autoService.findOne(id);
		
		if (auto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{code: 400, message: 'No existe auto con el id " + id + "'}");
		}
		
		auto.setTipo(tipo);
		auto.setOpcionales(listaDeOpcionales);
		auto = autoService.update(auto);
		return ResponseEntity.ok(gson.toJson(auto));    
	}
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public ResponseEntity<String> deleteAutos(@PathVariable("id") Integer id) {
		try {
			autoService.delete(id);
	        return ResponseEntity.ok("{code: 200, message: 'success' }");
		} catch (EmptyResultDataAccessException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{code: 400, message: 'No existe auto con el id " + id + "'}");
		}
    }
	
}
