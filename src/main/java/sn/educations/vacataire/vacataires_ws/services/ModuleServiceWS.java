package sn.educations.vacataire.vacataires_ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import sn.educations.vacataire.dao.modele.Module;
import sn.educations.vacataire.vacataires_ws.actions.ModuleServiceImp;

@RestController
public class ModuleServiceWS {
	
	@Autowired
	private ModuleServiceImp moduleService;
	
	//save or update
	@RequestMapping(value = "/saveModule", method= RequestMethod.GET)
	public String saveModule(@RequestParam( value="id",      required=false) Long   id,
			                 @RequestParam( value="libelle", required=false) String libelle) throws JsonProcessingException{
		
		Module module = new Module();
		if((id != null) && (moduleService.findById(id) != null))
			module = moduleService.findById(id);
		
		if((libelle != null) && (libelle.trim().length()> 0))
			module.setLibelle(libelle);
		
		module = moduleService.save(module);
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("jsonFilterModule", SimpleBeanPropertyFilter.serializeAllExcept());
		
		return objectMapper.writer(filters).writeValueAsString(module);
	}
	
	//Suppression
	@RequestMapping(value = "/deleteModule", method= RequestMethod.GET)
	public void deleteModule(@RequestParam( value="id", required=true)Long id){
		
		Module module = moduleService.findById(id);
		if(module != null)
			moduleService.delete(module);
		
	}
	
	//save or update
	@RequestMapping(value = "/rechercheModule", method= RequestMethod.GET)
	public String rechercheModule(@RequestParam( value="id",      required=false) Long   id,
			                 @RequestParam( value="libelle", required=false) String libelle) throws JsonProcessingException{
		
		List<Module> modules = moduleService.getModules(id, libelle);
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("jsonFilterModule", SimpleBeanPropertyFilter.serializeAllExcept());
		
		return objectMapper.writer(filters).writeValueAsString(modules);
	}

}
