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
    public String getAutos() {
		List<Auto> autos = autoService.findAll();
		return gson.toJson(autos);
    }
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.GET)
	@ResponseBody
    public Auto getOneAutos(@PathVariable("id") Integer id) {
		return autoService.findOne(id);
    }
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.POST)
	@ResponseBody
    public String postAutos(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		Tipo tipo = tipoService.findBy(nombre);

		List<String> siglas = Arrays.asList(opcionales.split(","));
		List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
		
		Auto auto = new Auto(tipo, listaDeOpcionales);
		auto = autoService.save(auto);
		return gson.toJson(auto);
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public String putAutos(@PathVariable("id") Integer id, @RequestParam(value = "nombre") String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		Tipo tipo = tipoService.findBy(nombre);

		List<String> siglas = Arrays.asList(opcionales.split(","));
		List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
		
		Auto auto = autoService.findOne(id);
		auto.setTipo(tipo);
		auto.setOpcionales(listaDeOpcionales);
		auto = autoService.update(auto);
		return gson.toJson(auto);    
	}
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public String deleteAutos(@PathVariable("id") Integer id) {
        autoService.delete(id);
        return "{result: 'success'}";
    }
	
}
