package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.ChefDepartement;

public interface IChefDepartementService {
	public ChefDepartement save(ChefDepartement chefDapart);
	public void delete(ChefDepartement chefDapart);
	public ChefDepartement findById(Long id);
	public List<ChefDepartement> findByNom(String nom);
	public List<ChefDepartement> findAll();
}
