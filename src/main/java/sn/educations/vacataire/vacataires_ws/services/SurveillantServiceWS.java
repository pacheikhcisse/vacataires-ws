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
	
	//save or update
	@RequestMapping(value = "/saveSurveillant", method= RequestMethod.GET)
	public String saveSurveillant(@RequestParam( value="id",        required=false) Long   id,
			                      @RequestParam( value="prenom",    required=false) String prenom,
	                              @RequestParam( value="nom",       required=false) String nom,
	                              @RequestParam( value="telephone", required=false) String telephone,
	                              @RequestParam( value="email",     required=false) String email,
	                              @RequestParam( value="cv",        required=false) String cv) throws JsonProcessingException{
		
		Surveillant surveillant = new Surveillant();
		if((id!=null) && (surveillantService.findById(id)!=null))
			surveillant = surveillantService.findById(id);
		if(prenom!=null && prenom.trim().length()>0)
			surveillant.setPrenom(prenom);
		if(nom!=null && nom.trim().length()>0)
			surveillant.setNom(nom);
		if(telephone!=null && telephone.trim().length()>0)
			surveillant.setTelephone(telephone);
		if(email!=null && email.trim().length()>0)
			surveillant.setEmail(email);
		if(cv!=null && cv.trim().length()>0)
			surveillant.setCv(cv);
		
		surveillant = surveillantService.save(surveillant);
		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept())
				.addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("surveillant","departement"));
		
		return objectMapper.writer(filters).writeValueAsString(surveillant);
	}
	
	//Suppression surveillant
	@RequestMapping(value = "/deleteSurveillant", method= RequestMethod.GET)
	public void deleteSurveillant(@RequestParam( value="id", required=true)Long id) throws JsonProcessingException{
		Surveillant surveillant = surveillantService.findById(id);
		if(surveillant != null)
			surveillantService.delete(surveillant);
	}
	
	//Recherche surveillant
	@RequestMapping(value = "/rechercheSurveillant", method= RequestMethod.GET)
	public String findSurveillant(@RequestParam( value="id",       required=false)Long id, 
			                      @RequestParam( value="nom",      required=false)String nom, 
			                      @RequestParam( value="prenom",   required=false)String prenom, 
			                      @RequestParam( value="telephone",required=false)String telephone) throws JsonProcessingException{
		
		List<Surveillant> surveillants = surveillantService.getSurveillants(id, nom, prenom, telephone);
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("jsonFilterSurveillant", SimpleBeanPropertyFilter.serializeAllExcept())
				.addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("surveillant","departement"));
		return objectMapper.writer(filters).writeValueAsString(surveillants);
	}

}
