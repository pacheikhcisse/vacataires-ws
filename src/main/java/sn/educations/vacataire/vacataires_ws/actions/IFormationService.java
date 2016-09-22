package sn.educations.vacataire.vacataires_ws.actions;

import sn.educations.vacataire.dao.modele.Formation;

import java.util.List;

public interface IFormationService {
	public void delete(Formation formation);
	public Formation save(Formation formation);
	public Formation findById(Long id);
	public Formation findByLibelle(String libelle);
	public List<Formation> findAll();
	public List<Formation> getFormations(Long id,String libelle,Long idTypeFormation);
}
