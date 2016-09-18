package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.educations.vacataire.dao.modele.Classe;
import sn.educations.vacataire.dao.services.ClasseDaoServiceImp;

@Component
public class ClasseServiceImp implements IClasseService {

	@Autowired
	private ClasseDaoServiceImp classeDaoServiceImp;
	
	public Classe save(Classe classe) {
		return classeDaoServiceImp.save(classe);
	}

	public void delete(Classe classe) {
		classeDaoServiceImp.delete(classe);
	}

	public Classe findById(Long id) {
		return classeDaoServiceImp.findById(id);
	}

	public Classe findByLibelle(String libelle) {
		return classeDaoServiceImp.findByLibelle(libelle);
	}

	public List<Classe> findByNiveauEtude(String niveauEtude) {
		return classeDaoServiceImp.findByNiveauEtude(niveauEtude);
	}

	public List<Classe> findAll() {
		return classeDaoServiceImp.findAll();
	}

	public List<Classe> getClasses(Long id, String libelle, String niveauEtude, Long idCycle) {
		return classeDaoServiceImp.getClasses(id, libelle, niveauEtude, idCycle);
	}

}
