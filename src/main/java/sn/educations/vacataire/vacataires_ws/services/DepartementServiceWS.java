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

import sn.educations.vacataire.dao.modele.Auditeur;
import sn.educations.vacataire.dao.modele.ChefDepartement;
import sn.educations.vacataire.dao.modele.Departement;
import sn.educations.vacataire.vacataires_ws.actions.AuditeurServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.ChefDepartementServiceImpl;
import sn.educations.vacataire.vacataires_ws.actions.DepartementServiceImp;

@RestController
public class DepartementServiceWS {
	
	@Autowired
	private DepartementServiceImp departementService;
	@Autowired
	private ChefDepartementServiceImpl chefDepartementService;
	@Autowired
	private AuditeurServiceImp auditeurService;
	
	
	// Save or update departement
	@RequestMapping(value = "/saveDepartement", method= RequestMethod.GET )
	public String saveDepartement(@RequestParam( value="id", required=false)                    Long id,
			                         @RequestParam( value="nom", required=true)                 String nom,
									 @RequestParam( value="idChefDepartement", required=false)  Long idChefDepartement,
									 @RequestParam( value="idAuditeur", required=false)         Long idAuditeur) throws JsonProcessingException{
		
		Departement departement = new Departement();
		
		if((id != null) && departementService.findById(id)!=null)
			departement = departementService.findById(id);
				
		departement.setNom(nom);
		
		if(idChefDepartement != null){
			ChefDepartement chefDept = chefDepartementService.findById(idChefDepartement);
			if(chefDept != null)
				departement.setChefDepartement(chefDept);
		}
		
		if(idAuditeur != null){
			Auditeur audit = auditeurService.findById(idAuditeur);
			if(audit != null)
				departement.setAuditeur(audit);
		}
		 
		departement = departementService.save(new Departement(nom));
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				                    addFilter("jsonFilterDepartement", SimpleBeanPropertyFilter.serializeAllExcept());
		
		return objectMapper.writer(filters).writeValueAsString(departement);
	}
	
	//Suppression departement
	@RequestMapping(value = "/deleteDepartement", method= RequestMethod.GET )
	public void deleteDepartement(@RequestParam( value="id", required=true)Long id) throws JsonProcessingException{
		
		Departement dept = departementService.findById(id);
		if(dept != null)
			departementService.delete(dept);
	}
	
	//Recherche departement
	@RequestMapping(value = "/findDepartement", method= RequestMethod.GET )
	public String findDepartement(@RequestParam( value="id", required=false)      Long id,
			                          @RequestParam( value="nom", required=false) String nom) throws JsonProcessingException{
		if((id==null) && (nom == null))
			return findAllDepartement();
		return findOneDepartement(id, nom);
	}
		

	//Recherche un departement
    public String findOneDepartement(Long id,String nom) throws JsonProcessingException{
		Departement departement = null;
		if((id != null) && (nom != null)){
			departement = departementService.findById(id);
			if((departement != null) && (!departement.getNom().equalsIgnoreCase(nom)))
				departement = null;
			}
		else if (id != null)
			departement = departementService.findById(id);
		else if (nom != null)
			departement = departementService.findByNom(nom);
			
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				                    addFilter("jsonFilterDepartement", SimpleBeanPropertyFilter.serializeAllExcept()).
				                    addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("departement"));
		return objectMapper.writer(filters).writeValueAsString(departement);
	}
	
	//get all departement
	public String findAllDepartement() throws JsonProcessingException{
		List<Departement> departements = departementService.findAll();
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().
				                    addFilter("jsonFilterDepartement", SimpleBeanPropertyFilter.serializeAllExcept()).
				                    addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("departement"));
		return objectMapper.writer(filters).writeValueAsString(departements);
	}

}
