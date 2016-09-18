package sn.educations.vacataire.vacataires_ws.actions;

import java.util.List;

import sn.educations.vacataire.dao.modele.ModuleClasse;

public interface IModuleClasseService {
	public void delete(ModuleClasse moduleClasse);
	public ModuleClasse save(ModuleClasse moduleClasse);
	public ModuleClasse findByIds(Long idModule, Long idClasse);
	public List<ModuleClasse> findByVolumeHoraire(int volumeHoraire);
	public List<ModuleClasse> findByCoefficient(float coefficient);
	public List<ModuleClasse> getModuleClasses(Integer volumeHoraire,Float coefficient,Long idModule,Long idClasse, Long idFormation);
	public List<ModuleClasse> findAll();
}
