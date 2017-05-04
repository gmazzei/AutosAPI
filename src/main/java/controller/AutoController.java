package main.java.controller;

import java.util.List;

import main.java.domain.Auto;
import main.java.domain.Sedan;
import main.java.service.AutoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AutoController {
	
	
	private AutoService autoService = new AutoService();
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.GET)
	@ResponseBody
    public List<Auto> getAutos() {
		List<Auto> autos = autoService.findAll();
		return autos;
    }
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.GET)
	@ResponseBody
    public Auto getOneAutos(@PathVariable("id") Integer id) {
		Auto auto = new Sedan();
		auto.setId(3);
		return auto;
    }
	
	
	@RequestMapping(value = "/autos", method = RequestMethod.POST)
	@ResponseBody
    public String postAutos(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		return "{op: 'POST','nombre': '" + nombre + "', opcionales: '" + opcionales + "'}";
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public String putAutos(@PathVariable("id") Integer id, @RequestParam(value = "nombre") String nombre, @RequestParam(value = "opcionales", defaultValue = "") String opcionales) {
		return "{op: 'PUT', id: " + id + ", 'nombre': '" + nombre + "', opcionales: '" + opcionales + "'}";
    }
	
	
	@RequestMapping(value = "/autos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public String deleteAutos(@PathVariable("id") Integer id) {
        return "{op: 'DELETE', id: " + id + "}";
    }
	
}
