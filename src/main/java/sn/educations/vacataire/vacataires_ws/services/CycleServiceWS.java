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

import sn.educations.vacataire.dao.modele.Cycle;
import sn.educations.vacataire.dao.modele.Surveillant;
import sn.educations.vacataire.vacataires_ws.actions.CycleServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.SurveillantServiceImp;

@RestController
public class CycleServiceWS {
	
	@Autowired
	private CycleServiceImp cycleService;
	
	@Autowired
	private SurveillantServiceImp surveillantService;
	
	
	//save or update
	@RequestMapping(value = "/saveCycle", method= RequestMethod.GET )
	public String saveCycle(@RequestParam( value="id", required=false)            Long id,
			                @RequestParam( value="libelle", required=false)       String libelle,
			                @RequestParam( value="idSurveillant", required=false) Long idSurveillant) throws JsonProcessingException{
		
		Cycle cycle = new Cycle();
		if((id!=null) && (cycleService.findById(id)!= null))
			cycle = cycleService.findById(id);		
		
		if(libelle!=null && libelle.trim().length()>0)
			cycle.setLibelle(libelle);
		
		if(idSurveillant!= null){
			Surveillant surv = surveillantService.findById(idSurveillant);
			if(surv!=null)
				cycle.setSurveillant(surv);
		}
		
		cycle = cycleService.save(cycle);
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept()).
				addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept("cycles")).
				addFilter("jsonCycleDepartement", SimpleBeanPropertyFilter.serializeAllExcept("cycle")).
				addFilter("jsonFilterDepartement", SimpleBeanPropertyFilter.serializeAllExcept("cycleDepartements"));
		return objectMapper.writer(filters).writeValueAsString(cycle);
	}
	
	//Sppression cycle
	@RequestMapping(value = "/deleteCycle", method= RequestMethod.GET )
	public void deleteCycle(@RequestParam( value="id") Long id){
		
		Cycle cycle = cycleService.findById(id);
		if(cycle!=null)
			cycleService.delete(cycle);
	}
	
	//Recherche
	@RequestMapping(value = "/findCycle", method= RequestMethod.GET )
	public String findCycle(@RequestParam( value="id", required=false)     Long id,
			                @RequestParam( value="libelle",required=false) String libelle) throws JsonProcessingException{
		
		List<Cycle> cycles = cycleService.getCycles(id, libelle);
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept()).
				addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept("cycles")).
				addFilter("jsonCycleDepartement", SimpleBeanPropertyFilter.serializeAllExcept("cycle")).
				addFilter("jsonFilterDepartement", SimpleBeanPropertyFilter.serializeAllExcept("cycleDepartements"));
		return objectMapper.writer(filters).writeValueAsString(cycles);
	}
}
