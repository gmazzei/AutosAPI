package main.java.controller;

import java.util.Arrays;
import java.util.List;

import main.java.domain.Auto;
import main.java.domain.Opcional;
import main.java.domain.Tipo;
import main.java.json.AutoGson;
import main.java.service.AutoService;
import main.java.service.OpcionalService;
import main.java.service.TipoService;
import main.java.validation.AutoNoExisteException;
import main.java.validation.ResponseJsonBuilder;
import main.java.validation.TipoNoExisteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class AutoController {
	
	@Autowired
	private AutoService autoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	@Autowired
	private TipoService tipoService;
	
	private final Gson gson = new AutoGson().create();
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<String> getAutos() {
		List<Auto> autos = autoService.findAll();
		return ResponseEntity.ok(gson.toJson(autos));
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<String> getOneAutos(@PathVariable("id") Integer id) {
		try {
			Auto auto = autoService.findOne(id);
			validarId(auto, id);
			return ResponseEntity.ok(gson.toJson(auto));
		} catch (AutoNoExisteException ex) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		}
		
		
    }
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.POST)
	@ResponseBody
    public ResponseEntity<String> postAutos(@RequestParam(value = "nombre", required = true) String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		
		try {
			Tipo tipo = tipoService.findBy(nombre);
			validarTipo(tipo, nombre);
			
			List<String> siglas = Arrays.asList(opcionales.split(","));
			List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
			
			Auto auto = new Auto(tipo, listaDeOpcionales);
			auto = autoService.save(auto);
			return ResponseEntity.ok(gson.toJson(auto));
		} catch (TipoNoExisteException ex) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		}
		
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public ResponseEntity<String> putAutos(@PathVariable("id") Integer id, @RequestParam(value = "nombre", required = true) String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		try {
			Auto auto = autoService.findOne(id);
			validarId(auto, id);
			
			Tipo tipo = tipoService.findBy(nombre);
			validarTipo(tipo, nombre);
			auto.setTipo(tipo);
			
			List<String> siglas = Arrays.asList(opcionales.split(","));
			List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
			auto.setOpcionales(listaDeOpcionales);
			
			auto = autoService.update(auto);
			return ResponseEntity.ok(gson.toJson(auto));
			
		} catch (TipoNoExisteException ex) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		} catch (AutoNoExisteException ex) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		}
		    
	}
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public ResponseEntity<String> deleteAutos(@PathVariable("id") Integer id) {
		try {
			Auto auto = autoService.findOne(id);
			validarId(auto, id);
			autoService.delete(auto);
	        return ResponseEntity.ok(ResponseJsonBuilder.build(200, "success"));
		} catch (AutoNoExisteException ex) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		}
    }
	
	private void validarTipo(Tipo tipo, String nombre) {
		if (tipo == null) throw new TipoNoExisteException(nombre);
	}
	
	private void validarId(Auto auto, Integer id) {
		if (auto == null) throw new AutoNoExisteException(id);
	}
	
}
