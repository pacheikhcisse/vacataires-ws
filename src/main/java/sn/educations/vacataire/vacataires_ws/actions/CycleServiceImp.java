package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.educations.vacataire.dao.modele.Cycle;
import sn.educations.vacataire.dao.services.CycleDaoServiceImp;

@Component
public class CycleServiceImp implements ICycleService {
	
	@Autowired
	private CycleDaoServiceImp cycleDaoService;

	public Cycle save(Cycle cycle) {
		return cycleDaoService.save(cycle);
	}

	public void delete(Cycle cycle) {
		cycleDaoService.delete(cycle);
	}
	
	public Cycle findById(Long id) {
		return cycleDaoService.findById(id);
	}

	public Cycle findByLibelle(String libelle) {
		return cycleDaoService.findByLibelle(libelle);
	}

	public List<Cycle> findAll() {
		return cycleDaoService.findAll();
	}

	public List<Cycle> getCycles(Long id, String libelle) {
		return cycleDaoService.getCycles(id, libelle);
	}

}
