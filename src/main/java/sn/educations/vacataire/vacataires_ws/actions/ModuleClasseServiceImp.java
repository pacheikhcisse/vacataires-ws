package sn.educations.vacataire.vacataires_ws.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.educations.vacataire.dao.modele.ModuleClasse;
import sn.educations.vacataire.dao.services.ModuleClasseDaoServiceImp;

import java.util.List;

@Component
public class ModuleClasseServiceImp implements IModuleClasseService {

	@Autowired
	private ModuleClasseDaoServiceImp moduleClasseDaoServiceImp;
	
	public void delete(ModuleClasse moduleClasse) {
		moduleClasseDaoServiceImp.delete(moduleClasse);
	}

	public ModuleClasse save(ModuleClasse moduleClasse) {
		return moduleClasseDaoServiceImp.save(moduleClasse);
	}

	public ModuleClasse findByIds(Long idModule,Long idClasse) {
		return moduleClasseDaoServiceImp.findByIds(idModule,idClasse);
	}

	public List<ModuleClasse> findByVolumeHoraire(int volumeHoraire) {
		return moduleClasseDaoServiceImp.findByVolumeHoraire(volumeHoraire);
	}

	public List<ModuleClasse> findByCoefficient(float coefficient) {
		return moduleClasseDaoServiceImp.findByCoefficient(coefficient);
	}

	public List<ModuleClasse> getModuleClasses(Integer volumeHoraire, Float coefficient, Long idModule,
			Long idClasse, Long idFormation) {
		return moduleClasseDaoServiceImp.getModuleClasses(volumeHoraire, coefficient, idModule, idClasse,idFormation);
	}

	public List<ModuleClasse> findAll() {
		return moduleClasseDaoServiceImp.findAll();
	}

}
