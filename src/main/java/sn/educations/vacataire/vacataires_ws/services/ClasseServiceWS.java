package sn.educations.vacataire.vacataires_ws.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.educations.vacataire.dao.modele.Classe;
import sn.educations.vacataire.dao.modele.Cycle;
import sn.educations.vacataire.vacataires_ws.actions.ClasseServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.CycleServiceImp;

import java.util.List;

@RestController
public class ClasseServiceWS {
	
	@Autowired
	private ClasseServiceImp classeService;
	@Autowired
	private CycleServiceImp cycleService;
	
	//save or update
	@RequestMapping(value = "/saveClasse", method= RequestMethod.GET )
	public String saveClasse(@RequestParam( value="id",          required=false)Long id,
			                 @RequestParam( value="libelle",     required=false)String libelle,
			                 @RequestParam( value="niveauEtude", required=false)String niveauEtude,
			                 @RequestParam( value="idCycle",     required=false)Long idCycle) throws JsonProcessingException{
		
		Classe classe = new Classe();
		
		if((id!=null) && (classeService.findById(id)!=null))
			classe = classeService.findById(id);
		if((libelle!=null) && (libelle.trim().length()>0))
			classe.setLibelle(libelle);
		if((niveauEtude!=null) &&(niveauEtude.trim().length()>0))
			classe.setNiveauEtude(niveauEtude);
		
		if(idCycle!=null){
			Cycle cycle = cycleService.findById(idCycle);
			if(cycle!=null)
				classe.setCycle(cycle);
		}
		
		classe = classeService.save(classe);		
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("jsonfilterClasse", SimpleBeanPropertyFilter.serializeAllExcept("anneeScolaires"))
				.addFilter("jsonFilterModuleClasse", SimpleBeanPropertyFilter.serializeAllExcept("classe"))
				.addFilter("jsonFilterModule", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","peutEtreEnseigner"))
				.addFilter("jsonfilterCours", SimpleBeanPropertyFilter.serializeAllExcept("absences"))
				.addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("surveillant","cycleDepartements","classes"));
		
		return objectMapper.writer(filters).writeValueAsString(classe);
	}
	
	
	//save or update
	@RequestMapping(value = "/deleteClasse", method= RequestMethod.GET )
	public void deleteClasse(@RequestParam( value="id")Long id){
		
		Classe classe = classeService.findById(id);
		if(classe != null)
			classeService.delete(classe);
	}
	
	
	//save or update
	@RequestMapping(value = "/findClasse", method= RequestMethod.GET )
	public String findClasse(@RequestParam( value="id",          required=false)Long id,
			                 @RequestParam( value="libelle",     required=false)String libelle,
			                 @RequestParam( value="niveauEtude", required=false)String niveauEtude,
			                 @RequestParam( value="idCycle",     required=false)Long idCycle) throws JsonProcessingException{
		
		List<Classe> classes = classeService.getClasses(id, libelle, niveauEtude, idCycle);
		ObjectMapper objectMapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("jsonfilterClasse", SimpleBeanPropertyFilter.serializeAllExcept("anneeScolaires"))
				.addFilter("jsonFilterModuleClasse", SimpleBeanPropertyFilter.serializeAllExcept("classe"))
				.addFilter("jsonFilterModule", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","peutEtreEnseigner"))
				.addFilter("jsonfilterCours", SimpleBeanPropertyFilter.serializeAllExcept("absences"))
				.addFilter("jsonfilterCycle", SimpleBeanPropertyFilter.serializeAllExcept("surveillant","cycleDepartements","classes"));
		
		return objectMapper.writer(filters).writeValueAsString(classes);
	}
		
			                       

}
