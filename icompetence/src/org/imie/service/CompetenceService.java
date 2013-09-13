package org.imie.service;

import java.util.List;

import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.MotClefDTO;
import org.imie.DTO.NiveauDTO;
import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class CompetenceService extends ATransactional implements ICompetenceService{

	@Override
	public List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		return competenceDAO.getCompetenceByUser(userDTO);
	}

	
	public List<CompetenceDTO> findAll()
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);		
		return competenceDAO.findAll();
	}

	@Override
	public CompetenceDTO findById(Integer competenceid)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);		
		return competenceDAO.findById(competenceid);
	}

	
	public CompetenceDTO insertCompetence(CompetenceDTO competenceToInsert)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);		
		return competenceDAO.insertCompetence(competenceToInsert);
	}

	
	public CompetenceDTO updateCompetence(CompetenceDTO competenceToUpdate)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);		
		return competenceDAO.updateCompetence(competenceToUpdate);
	}

	
	public void deleteCompetence(CompetenceDTO competenceToDelete)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);	
		competenceDAO.deleteCompetence(competenceToDelete);
	}


	public Boolean creerCompUserNiveau(UserDTO userDTO,
			CompetenceDTO competenceDTO, NiveauDTO niveauDTO)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		return competenceDAO.creerCompUserNiveau(userDTO, competenceDTO, niveauDTO);
	}


	public void modifierCompUserNiveau(UserDTO userDTO,
			CompetenceDTO competenceDTO, NiveauDTO niveauDTO)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		competenceDAO.modifierCompUserNiveau(userDTO, competenceDTO, niveauDTO);
	}


	public void supprimerCompUserNiveau(UserDTO userDTO,
			CompetenceDTO competenceDTO, NiveauDTO niveauDTO)
			throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		competenceDAO.supprimerCompUserNiveau(userDTO, competenceDTO, niveauDTO);
	}



	public Boolean creerCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		return competenceDAO.creerCompMotClef(competenceDTO, mot_clefDTO);
	}


	
	public void modifierCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		competenceDAO.modifierCompMotClef(competenceDTO, mot_clefDTO);
	}


	public void supprimerCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		competenceDAO.supprimerCompMotClef(competenceDTO, mot_clefDTO);
	}


	public List<CompetenceDTO> findAllArbo() throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		return competenceDAO.findAllArbo();
	}



	public List<CompetenceDTO> findArboFilsPere(Integer id)	throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		return competenceDAO.findArboFilsPere(id);
	}


	
	public List<CompetenceDTO> findByNom(String competencenom) throws TransactionalConnectionException {
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		return competenceDAO.findByNom(competencenom);
	}


	
	

}
