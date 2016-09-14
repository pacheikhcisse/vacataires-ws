package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.Departement;

public interface IDepartementService {
	public Departement save(Departement departement);
	public void delete(Departement departement);
	public Departement findById(Long id);
	public Departement findByNom(String nom);
	public List<Departement> getDepartements(Long id, String nom);
	public List<Departement> findAll(); 
}
