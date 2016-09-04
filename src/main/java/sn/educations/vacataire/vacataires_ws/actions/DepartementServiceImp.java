package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.educations.vacataire.dao.modele.Departement;
import sn.educations.vacataire.dao.services.DepartementDaoServiceImp;

@Component
public class DepartementServiceImp implements IDepartementService {
	
	@Autowired
	private DepartementDaoServiceImp departementDaoservice;

	public Departement save(Departement departement) {
		return departementDaoservice.save(departement);
	}

	public void delete(Departement departement) {
		departementDaoservice.delete(departement);
	}

	public Departement findById(Long id) {
		return departementDaoservice.findById(id);
	}

	public Departement findByNom(String nom) {
		return departementDaoservice.findByNom(nom);
	}

	public List<Departement> findAll() {
		return departementDaoservice.findAll();
	}

}
