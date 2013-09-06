package org.imie.service;

import java.util.List;

import org.imie.DAO.interfaces.INiveauDAO;
import org.imie.DTO.NiveauDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.INiveauService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class NiveauService extends ATransactional implements INiveauService{
	

	public List<NiveauDTO> findAll() throws TransactionalConnectionException {
		INiveauDAO niveauDAO = BaseConcreteFactory.getInstance().createNiveauDAO(this);		
		return niveauDAO.findAll();
	}
	
	public NiveauDTO findbyid(Integer niveauid)
			throws TransactionalConnectionException {
		INiveauDAO niveauDAO = BaseConcreteFactory.getInstance().createNiveauDAO(this);
		return niveauDAO.findById(niveauid);
	}

	
	public NiveauDTO insertNiveau(NiveauDTO niveauToInsert)
			throws TransactionalConnectionException {
		INiveauDAO niveauDAO = BaseConcreteFactory.getInstance().createNiveauDAO(this);
		return niveauDAO.insertNiveau(niveauToInsert);
	}

	
	public NiveauDTO updateNiveau(NiveauDTO niveauToUpdate)
			throws TransactionalConnectionException {
		INiveauDAO niveauDAO = BaseConcreteFactory.getInstance().createNiveauDAO(this);
		return niveauDAO.updateNiveau(niveauToUpdate);
	}


	public void deleteNiveau(NiveauDTO niveauToDelete)
			throws TransactionalConnectionException {
		INiveauDAO niveauDAO = BaseConcreteFactory.getInstance().createNiveauDAO(this);		
		
		niveauDAO.deleteNiveau(niveauToDelete);
	}

}
