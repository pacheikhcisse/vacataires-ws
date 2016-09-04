package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.educations.vacataire.dao.modele.ChefDepartement;
import sn.educations.vacataire.dao.services.ChefDepartementDaoServiceImp;

@Component
public class ChefDepartementServiceImpl implements IChefDepartementService {
	
	@Autowired
	private ChefDepartementDaoServiceImp chefDepartementDaoService;

	@Override
	public ChefDepartement save(ChefDepartement chefDapart) {
		return chefDepartementDaoService.save(chefDapart);
	}

	@Override
	public void delete(ChefDepartement chefDapart) {
		chefDepartementDaoService.delete(chefDapart);
	}

	@Override
	public ChefDepartement findById(Long id) {
		return chefDepartementDaoService.findById(id);
	}

	@Override
	public List<ChefDepartement> findByNom(String nom) {
		return chefDepartementDaoService.findByNom(nom);
	}

	@Override
	public List<ChefDepartement> findAll() {
		return chefDepartementDaoService.findAll();
	}

}
