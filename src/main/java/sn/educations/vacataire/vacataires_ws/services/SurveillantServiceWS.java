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

import sn.educations.vacataire.dao.modele.Surveillant;
import sn.educations.vacataire.vacataires_ws.actions.SurveillantServiceImp;

@RestController
public class SurveillantServiceWS {
	
	@Autowired
	private SurveillantServiceImp surveillantService;
	
	// Save or update surveillant
	@RequestMapping(value = "/saveSurveillant", method= RequestMethod.GET )
	public String saveSurveillant(@RequestParam( value="id", required=false) Long id,
            					  @RequestParam( value="prenom", required=true) String prenom,
            					  @RequestParam( value="nom", required=true) String nom,
            					  @RequestParam( value="telephone", required=true) String telephone,
            					  @RequestParam( value="email", required=true) String email,
            					  @RequestParam( value="cv", required=true) String cv) throws JsonProcessingException{
		
		Surveillant surveillant = new Surveillant();
		
		if((id != null) && surveillantService.findById(id)!=null)
			surveillant = surveillantService.findById(id);
		
		surveillant.setPrenom(prenom);
		surveillant.setNom(nom);
		surveillant.setTelephone(telephone);
		surveillant.setEmail(email);
		surveillant.setCv(cv);
		
		surveillant = surveillantService.save(new Surveillant(prenom, nom, telephone, email, cv));
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				                    addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept());
		
		return objectMapper.writer(filters).writeValueAsString(surveillant);
	}
	
	//Suppression surveillant
	@RequestMapping(value = "/deleteSurveillant", method= RequestMethod.GET )
	public void deleteSurveillant(@RequestParam( value="id", required=true)Long id) throws JsonProcessingException{
		
		Surveillant surv = surveillantService.findById(id);
		if(surv != null)
			surveillantService.delete(surv);
	}
	
	//Recherche surveillant
	@RequestMapping(value = "/findSurveillant", method= RequestMethod.GET )
	public String findSurveillant(@RequestParam( value="id", required=false)  Long id,
			                      @RequestParam( value="nom", required=false) String nom) throws JsonProcessingException{
		if((id==null) && (nom == null))
			return findAllSurveillant();
		return findOneSurveillant(id, nom);
	}
	
	//Recherche un surveillant
    public String findOneSurveillant(Long id,String nom) throws JsonProcessingException{
		Surveillant surveillant = null;
		if((id != null) && (nom != null)){
			surveillant = surveillantService.findById(id);
			if((surveillant != null) && (!surveillant.getNom().equalsIgnoreCase(nom)))
				surveillant = null;
			}
		else if (id != null)
			surveillant = surveillantService.findById(id);
		else if (nom != null)
			surveillant = (Surveillant) surveillantService.findByNom(nom);
			
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				                    addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept()).
				                    addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("surveillant"));
		return objectMapper.writer(filters).writeValueAsString(surveillant);
	}
	
	//get all surveillant
	public String findAllSurveillant() throws JsonProcessingException{
		List<Surveillant> surveillants = surveillantService.findAll();
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				                    addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept()).
				                    addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("surveillant"));
		return objectMapper.writer(filters).writeValueAsString(surveillants);
	}

}
