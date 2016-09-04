package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.educations.vacataire.dao.modele.Module;
import sn.educations.vacataire.dao.services.ModuleDaoServiceImp;

@Component
public class ModuleServiceImp implements IModuleService {
	
	@Autowired
	private ModuleDaoServiceImp moduledaoservice;

	public void delete(Module module) {
		moduledaoservice.delete(module);
	}

	public Module save(Module module) {
		return moduledaoservice.save(module);
	}

	public Module findById(Long id) {
		return moduledaoservice.findById(id);
	}

	public Module findByLibelle(String libelle) {
		return moduledaoservice.findByLibelle(libelle);
	}

	public List<Module> findAll() {
		return moduledaoservice.findAll();
	}

}
