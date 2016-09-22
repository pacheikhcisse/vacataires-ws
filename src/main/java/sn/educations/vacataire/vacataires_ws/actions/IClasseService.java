package sn.educations.vacataire.vacataires_ws.actions;

import sn.educations.vacataire.dao.modele.Classe;

import java.util.List;

public interface IClasseService {
	public Classe save(Classe classe);
	public void delete(Classe classe);
	public Classe findById(Long id);
	public Classe findByLibelle(String libelle);
	public List<Classe> findByNiveauEtude(String niveauEtude);
	public List<Classe> findAll();
	public List<Classe> getClasses(Long id, String libelle, String niveauEtude, Long idCycle);
}
