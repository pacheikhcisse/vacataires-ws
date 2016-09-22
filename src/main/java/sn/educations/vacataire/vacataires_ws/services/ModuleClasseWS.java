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
import sn.educations.vacataire.dao.modele.Formation;
import sn.educations.vacataire.dao.modele.Module;
import sn.educations.vacataire.dao.modele.ModuleClasse;
import sn.educations.vacataire.vacataires_ws.actions.ClasseServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.FormationServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.ModuleClasseServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.ModuleServiceImp;

import java.util.List;

@RestController
public class ModuleClasseWS {
	
	@Autowired
	private ModuleClasseServiceImp moduleClasseService;
	@Autowired
	private ModuleServiceImp moduleService;
	@Autowired
	private ClasseServiceImp classeService;
	@Autowired
	private FormationServiceImp formationService;
	
		//save or update
		@RequestMapping(value = "/saveModuleClasse", method= RequestMethod.GET )
		public String saveModuleClasse(@RequestParam( value="volumeHoraire", required=false)Integer volumeHoraire,
				                       @RequestParam( value="coefficient",   required=false)Float coefficient,
				                       @RequestParam( value="idModule",      required=true)Long idModule,
				                       @RequestParam( value="idClasse",      required=true)Long idClasse,
				                       @RequestParam( value="idFormation",   required=false)Long idFormation) throws JsonProcessingException{
			
			ModuleClasse moduleClasse = new ModuleClasse();
			
			if((idModule!=null) && (idClasse != null)){
				ModuleClasse modClass = moduleClasseService.findByIds(idModule, idClasse);
				if(modClass != null)
					moduleClasse = modClass;
				else{
					Module module = moduleService.findById(idModule);
					if(module != null)
						moduleClasse.setModule(module);
					Classe classe = classeService.findById(idClasse);
					if(classe != null)
						moduleClasse.setClasse(classe);					
				}
			}
			
			if(volumeHoraire != null)
				moduleClasse.setVolumeHoraire(volumeHoraire);
			if(coefficient != null)
				moduleClasse.setCoefficient(coefficient);
			
			if(idFormation != null){
				Formation formation = formationService.findById(idFormation);
				if(formation != null)
					moduleClasse.setFormation(formation);
			}
			moduleClasse = moduleClasseService.save(moduleClasse);
			
			ObjectMapper objectMapper = new ObjectMapper();
			FilterProvider filters = new SimpleFilterProvider()
					              .addFilter("jsonFilterModuleClasse", SimpleBeanPropertyFilter.serializeAllExcept())
					              .addFilter("jsonFilterModule", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","peutEtreEnseigner"))
					              .addFilter("jsonfilterClasse", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","cours","AnneeScolaires"))
					              .addFilter("jsonfilterFormation", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","typeFormation"))
					              .addFilter("jsonFilterModuleClasseEnseignant", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","enseignant"));
			
			return objectMapper.writer(filters).writeValueAsString(moduleClasse);
		}
	
	    //delete
		@RequestMapping(value = "/deleteModuleClasse", method= RequestMethod.GET )
		public void deleteModuleClasse(@RequestParam( value="idModule")Long idModule,
				                       @RequestParam( value="idClasse")Long idClasse){
			
			ModuleClasse moduleClasse = moduleClasseService.findByIds(idModule, idClasse);
			if(moduleClasse != null)
				moduleClasseService.delete(moduleClasse);
		}
		
		
		//save or update
		@RequestMapping(value = "/findModuleClasse", method= RequestMethod.GET )
		public String findModuleClasse(@RequestParam( value="volumeHoraire", required=false)Integer volumeHoraire,
				                       @RequestParam( value="coefficient",   required=false)Float coefficient,
				                       @RequestParam( value="idModule",      required=false)Long idModule,
				                       @RequestParam( value="idClasse",      required=false)Long idClasse,
				                       @RequestParam( value="idFormation",   required=false)Long idFormation) throws JsonProcessingException{
			
			List<ModuleClasse> moduleClasses = moduleClasseService.getModuleClasses(volumeHoraire, coefficient, idModule, idClasse,idFormation);
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			FilterProvider filters = new SimpleFilterProvider()
					              .addFilter("jsonFilterModuleClasse", SimpleBeanPropertyFilter.serializeAllExcept())
					              .addFilter("jsonFilterModule", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","peutEtreEnseigner"))
					              .addFilter("jsonfilterClasse", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","cours","AnneeScolaires"))
					              .addFilter("jsonfilterFormation", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","typeFormation"))
					              .addFilter("jsonFilterModuleClasseEnseignant", SimpleBeanPropertyFilter.serializeAllExcept("moduleClasses","enseignant"));
			
			return objectMapper.writer(filters).writeValueAsString(moduleClasses);
		}
}
