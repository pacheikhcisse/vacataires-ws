package sn.educations.vacataire.vacataires_ws.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.educations.vacataire.dao.modele.Auditeur;
import sn.educations.vacataire.dao.modele.ChefDepartement;
import sn.educations.vacataire.dao.modele.Cycle;
import sn.educations.vacataire.dao.modele.Departement;
import sn.educations.vacataire.vacataires_ws.actions.AuditeurServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.ChefDepartementServiceImpl;
import sn.educations.vacataire.vacataires_ws.actions.DepartementServiceImp;

import java.util.List;

@RestController
@Slf4j
public class DepartementServiceWS {
	
	@Autowired
	private DepartementServiceImp departementService;
	@Autowired
	private ChefDepartementServiceImpl chefDepartementService;
	@Autowired
	private AuditeurServiceImp auditeurService;


	@ApiOperation(value = "Retrieve all departements")
	@RequestMapping(value="/departements", method = RequestMethod.GET)
	public List<Departement> findAll() {
		log.info("Find all departements");
		return departementService.findAll();
	}

	@ApiOperation(value = "Create a departement")
	@RequestMapping(value="/departements", method = RequestMethod.POST)
	public ResponseEntity saveDepartement(@RequestBody Departement departement) {
		log.info("Create departement [{}]" + departement);
		departementService.save(departement);
		return new ResponseEntity(departement, HttpStatus.CREATED);
	}

	
	//Suppression departement
	@RequestMapping(value = "/deleteDepartement", method= RequestMethod.GET )
	public void deleteDepartement(@RequestParam( value="id")Long id) throws JsonProcessingException{
		
		Departement dept = departementService.findById(id);
		if(dept != null)
			departementService.delete(dept);
	}
	
	//Recherche departement
	@RequestMapping(value = "/findDepartement", method= RequestMethod.GET )
	public String findDepartement(@RequestParam( value="id", required=false)  Long id,
			                      @RequestParam( value="nom", required=false) String nom) throws JsonProcessingException{
		
		List<Departement> departements = departementService.getDepartements(id, nom);
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
								.addFilter("jsonFilterDepartement", SimpleBeanPropertyFilter.serializeAllExcept())
				                .addFilter("jsonCycleDepartement", SimpleBeanPropertyFilter.serializeAllExcept("departement"))
								.addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("cycleDepartements","surveillant"));
		return objectMapper.writer(filters).writeValueAsString(departements);
	}

}
