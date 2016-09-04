package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.Auditeur;

public interface IAuditeurService {
	public void delete(Auditeur auditaur);
	public Auditeur save(Auditeur auditeur);
	public Auditeur findById(Long id);
	public List<Auditeur> findByNom(String nom);
	public List<Auditeur> findAll();
}
