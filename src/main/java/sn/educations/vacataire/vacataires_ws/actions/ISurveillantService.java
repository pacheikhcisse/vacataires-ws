package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.Surveillant;

public interface ISurveillantService {
	public void delete(Surveillant surveillant);
	public Surveillant save(Surveillant surveillant);
	public Surveillant findById(Long id);
	public List<Surveillant> findByNom(String nom);
	public List<Surveillant> findAll();
}
