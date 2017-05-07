package main.java.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import main.java.validation.FaltaParametroObligatorioException;
import main.java.validation.ResponseJsonBuilder;
import main.java.validation.TipoNoExisteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    public ResponseEntity<String> get() {
		List<Auto> autos = autoService.findAll();
		return ResponseEntity.ok(gson.toJson(autos));
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<String> get(@PathVariable("id") Integer id) {
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
    public ResponseEntity<String> post(@RequestBody(required = true) String body) {
		
		try {
			JsonObject json = gson.fromJson(body, JsonObject.class);
			
			String nombre = obtenerNombre(json);
			Tipo tipo = tipoService.findBy(nombre);
			validarTipo(tipo, nombre);
			
			
			List<String> siglas = obtenerOpcionales(json);
			List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
			
			Auto auto = new Auto(tipo, listaDeOpcionales);
			auto = autoService.save(auto);
			return ResponseEntity.created(createURI(auto.getId())).body(gson.toJson(auto));
			
		} catch (FaltaParametroObligatorioException | TipoNoExisteException ex) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		} catch (Exception ex) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		}
		
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public ResponseEntity<String> put(@PathVariable("id") Integer id, @RequestBody(required = true) String body) {
		try {
			Auto auto = autoService.findOne(id);
			validarId(auto, id);
			
			JsonObject json = gson.fromJson(body, JsonObject.class);
			
			String nombre = obtenerNombre(json);
			Tipo tipo = tipoService.findBy(nombre);
			validarTipo(tipo, nombre);
			auto.setTipo(tipo);
			
			List<String> siglas = obtenerOpcionales(json);
			List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
			auto.setOpcionales(listaDeOpcionales);
			
			auto = autoService.update(auto);
			return ResponseEntity.ok(gson.toJson(auto));

		} catch (FaltaParametroObligatorioException | TipoNoExisteException ex) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		} catch (AutoNoExisteException ex) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		} catch (Exception ex) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return ResponseEntity.status(status).body(ResponseJsonBuilder.build(status.value(), ex.getMessage()));
		}
		    
	}
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		try {
			Auto auto = autoService.findOne(id);
			validarId(auto, id);
			autoService.delete(auto);
	        return ResponseEntity.noContent().build();
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
	
	private String obtenerNombre(JsonObject json) {
		if (json.get("nombre") == null) throw new FaltaParametroObligatorioException("nombre");
		
		return json.get("nombre").getAsString();
	}
	
	private List<String> obtenerOpcionales(JsonObject json) {
		if (json.get("opcionales") == null) throw new FaltaParametroObligatorioException("opcionales");
		
		ArrayList<String> lista = new ArrayList<String>();
		
		JsonArray array = json.get("opcionales").getAsJsonArray();
		for (JsonElement arrayElement : array) {
			lista.add(arrayElement.getAsString());
		}
		
		return lista;
	}
	
	private URI createURI(Integer id) throws URISyntaxException {
		return new URI("/autos/" + id);
	}
}
