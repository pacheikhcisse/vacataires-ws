package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.Module;

public interface IModuleService {
	public void delete(Module module);
	public Module save(Module module);
	public Module findById(Long id);
	public Module findByLibelle(String libelle);
	public List<Module> getModules(Long id,String libelle);
	public List<Module> findAll();
}
