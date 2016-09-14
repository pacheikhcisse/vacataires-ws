package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.educations.vacataire.dao.modele.Surveillant;
import sn.educations.vacataire.dao.services.SurveillantDaoServiceImp;

@Component
public class SurveillantServiceImp implements ISurveillantService {
	
	@Autowired
	private SurveillantDaoServiceImp surveillantDaoService;

	public void delete(Surveillant surveillant) {
		surveillantDaoService.delete(surveillant);
	}

	public Surveillant save(Surveillant surveillant) {
		return surveillantDaoService.save(surveillant);
	}

	public Surveillant findById(Long id) {
		return surveillantDaoService.findById(id);
	}

	public List<Surveillant> findByNom(String nom) {
		return surveillantDaoService.findByNom(nom);
	}

	public List<Surveillant> findAll() {
		return surveillantDaoService.findAll();
	}

	@Override
	public List<Surveillant> getSurveillants(Long id, String nom, String prenom, String telephone) {
		return surveillantDaoService.getSurveillant(id, nom, prenom, telephone);
	}

}
