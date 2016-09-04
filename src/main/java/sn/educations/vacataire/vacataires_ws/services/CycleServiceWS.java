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
import sn.educations.vacataire.dao.modele.Departement;
import sn.educations.vacataire.dao.modele.Surveillant;
import sn.educations.vacataire.vacataires_ws.actions.CycleServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.DepartementServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.SurveillantServiceImp;

@RestController
public class CycleServiceWS {
	
	@Autowired
	private CycleServiceImp cycleService;
	@Autowired
	private DepartementServiceImp departementService;
	@Autowired
	private SurveillantServiceImp surveillantService;
	
	
	//save or update
	@RequestMapping(value = "/saveCycle", method= RequestMethod.GET )
	public String saveCycle(@RequestParam( value="id")                            Long id,
			                @RequestParam( value="libelle", required=true)        String libelle,
			                @RequestParam( value="idDepartement", required=false) Long idDepartement,
			                @RequestParam( value="idSurveillant", required=false) Long idSurveillant) throws JsonProcessingException{
		
		Cycle cycle = new Cycle();
		if((id!=null) && (cycleService.findById(id)!= null))
			cycle = cycleService.findById(id);		
		cycle.setLibelle(libelle);
		
		if(!idDepartement.equals(null)){
			Departement dept = departementService.findById(idDepartement);
			if(dept != null)
				cycle.setDepartement(dept);
		}
		
		if(idSurveillant!= null){
			Surveillant surv = surveillantService.findById(idSurveillant);
			if(surv!=null)
				cycle.setSurveillant(surv);
		}
		
		cycle = cycleService.save(cycle);
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("departement"));
		return objectMapper.writer(filters).writeValueAsString(cycle);
	}
	
	//Sppression cycle
	@RequestMapping(value = "/deleteCycle", method= RequestMethod.GET )
	public void deleteCycle(@RequestParam( value="id", required=true) Long id){
		
		Cycle cycle = cycleService.findById(id);
		if(cycle!=null)
			cycleService.delete(cycle);
	}
	
	//Recherche
	@RequestMapping(value = "/findCycle", method= RequestMethod.GET )
	public String findCycle(@RequestParam( value="id")      Long id,
			                @RequestParam( value="libelle") String libelle) throws JsonProcessingException{
		
		Cycle cycle = null;
		if((id!= null) && (libelle!=null)){
			cycle = cycleService.findById(id);
			if((cycle != null) && (!cycle.getLibelle().equalsIgnoreCase(libelle)))
				cycle = null;
		}
		else if(id!=null)
			cycle = cycleService.findById(id);
		else if(libelle != null)
			cycle = cycleService.findByLibelle(libelle);
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("departement"));
		return objectMapper.writer(filters).writeValueAsString(cycle);			
	}
	
	//get All cycles
	@RequestMapping(value = "/findAllCycle", method= RequestMethod.GET )
	public String findAllCycle() throws JsonProcessingException{
		
		List<Cycle> cycles = cycleService.findAll();
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("departement"));
		return objectMapper.writer(filters).writeValueAsString(cycles);			
	}
	
	

}
