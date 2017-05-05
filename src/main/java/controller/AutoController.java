package main.java.controller;

import java.util.Arrays;
import java.util.List;

import main.java.domain.Auto;
import main.java.domain.Opcional;
import main.java.domain.Tipo;
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

@Controller
public class AutoController {
	
	@Autowired
	private AutoService autoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	@Autowired
	private TipoService tipoService;
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.GET)
	@ResponseBody
    public List<Auto> getAutos() {
		List<Auto> autos = autoService.findAll();
		return autos;
    }
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.GET)
	@ResponseBody
    public Auto getOneAutos(@PathVariable("id") Integer id) {
		return autoService.findOne(id);
    }
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.POST)
	@ResponseBody
    public Auto postAutos(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		Tipo tipo = tipoService.findBy(nombre);

		List<String> siglas = Arrays.asList(opcionales.split(","));
		List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
		
		Auto auto = new Auto(tipo, listaDeOpcionales);
		auto = autoService.save(auto);
		return auto;
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public Auto putAutos(@PathVariable("id") Integer id, @RequestParam(value = "nombre") String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		Tipo tipo = tipoService.findBy(nombre);

		List<String> siglas = Arrays.asList(opcionales.split(","));
		List<Opcional> listaDeOpcionales = opcionalService.findBySiglaIn(siglas);
		
		Auto auto = autoService.findOne(id);
		auto = new Auto(tipo, listaDeOpcionales);
		auto = autoService.save(auto);
		return auto;    
	}
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public String deleteAutos(@PathVariable("id") Integer id) {
        autoService.delete(id);
        return "{result: 'success'}";
    }
	
}
