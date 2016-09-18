package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.educations.vacataire.dao.modele.Formation;
import sn.educations.vacataire.dao.services.FormationDaoServiceImp;

@Component
public class FormationServiceImp implements IFormationService {

	@Autowired
	private FormationDaoServiceImp formationDaoService;
	
	public void delete(Formation formation) {
		formationDaoService.delete(formation);
	}

	public Formation save(Formation formation) {
		return formationDaoService.save(formation);
	}

	public Formation findById(Long id) {
		return formationDaoService.findById(id);
	}

	public Formation findByLibelle(String libelle) {
		return formationDaoService.findByLibelle(libelle);
	}

	public List<Formation> findAll() {
		return formationDaoService.findAll();
	}

	public List<Formation> getFormations(Long id, String libelle, Long idTypeFormation) {
		return formationDaoService.getFormations(id, libelle, idTypeFormation);
	}

}
