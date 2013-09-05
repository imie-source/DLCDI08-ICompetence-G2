package org.imie.service;

import java.util.List;


import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DTO.CursusDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICursusService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class CursusService extends ATransactional implements ICursusService {

	
	public List<CursusDTO> findAll() throws TransactionalConnectionException{
		ICursusDAO cursusDAO = BaseConcreteFactory.getInstance().createCursusDAO(this);
		return cursusDAO.findAll();
	}
	
	public CursusDTO findbyid(Integer cursusid) throws TransactionalConnectionException {
		ICursusDAO cursusDAO = BaseConcreteFactory.getInstance().createCursusDAO(this);
		return cursusDAO.findById(cursusid);
	}
}
