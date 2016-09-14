package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.Surveillant;

public interface ISurveillantService {
	public void delete(Surveillant surveillant);
	public Surveillant save(Surveillant surveillant);
	public List<Surveillant> findAll();
	public List<Surveillant> getSurveillants(Long id,String nom,String prenom,String telephone);
}
