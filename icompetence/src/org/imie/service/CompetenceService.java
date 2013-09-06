package org.imie.service;

import java.util.List;

import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DTO.CompetenceDTO;
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


	
	

}
