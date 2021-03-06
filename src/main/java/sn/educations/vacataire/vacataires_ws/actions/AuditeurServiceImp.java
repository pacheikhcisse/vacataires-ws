package sn.educations.vacataire.vacataires_ws.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.educations.vacataire.dao.modele.Auditeur;
import sn.educations.vacataire.dao.services.AuditeurDaoServiceImp;

import java.util.List;

@Component
public class AuditeurServiceImp implements IAuditeurService {
	
	@Autowired
	private AuditeurDaoServiceImp auditeurDaoService;

	@Override
	public void delete(Auditeur auditeur) {
		auditeurDaoService.delete(auditeur);
	}

	@Override
	public Auditeur save(Auditeur auditeur) {
		return auditeurDaoService.save(auditeur);
	}

	@Override
	public Auditeur findById(Long id) {
		return auditeurDaoService.findById(id);
	}

	@Override
	public List<Auditeur> findByNom(String nom) {
		return auditeurDaoService.findByNom(nom);
	}

	@Override
	public List<Auditeur> findAll() {
		return auditeurDaoService.findAll();
	}

}
