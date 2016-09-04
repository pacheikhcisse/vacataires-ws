package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.Cycle;

public interface ICycleService {
	public Cycle save(Cycle cycle);
	public void delete(Cycle cycle);
	public Cycle findById(Long id);
	public Cycle findByLibelle(String libelle);
	public List<Cycle> findAll();
	
}
