package org.imie.service;

import java.util.List;

import org.imie.DAO.interfaces.IMotClefDAO;
import org.imie.DTO.MotClefDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IMotClefService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class MotClefService extends ATransactional implements IMotClefService {

	public List<MotClefDTO> findAll() throws TransactionalConnectionException {
		IMotClefDAO motClefDAO = BaseConcreteFactory.getInstance()
				.createMotClefDAO(this);
		return motClefDAO.findAll();
	}

	public MotClefDTO findById(Integer motClefid)
			throws TransactionalConnectionException {
		IMotClefDAO motClefDAO = BaseConcreteFactory.getInstance()
				.createMotClefDAO(this);
		return motClefDAO.findById(motClefid);
	}

	public MotClefDTO insertmotClef(MotClefDTO motClefToInsert)
			throws TransactionalConnectionException {
		IMotClefDAO motClefDAO = BaseConcreteFactory.getInstance()
				.createMotClefDAO(this);
		return motClefDAO.insertmotClef(motClefToInsert);
	}

	public MotClefDTO updatemotClef(MotClefDTO motClefToUpdate)
			throws TransactionalConnectionException {
		IMotClefDAO motClefDAO = BaseConcreteFactory.getInstance()
				.createMotClefDAO(this);
		return motClefDAO.updatemotClef(motClefToUpdate);
	}

	public void deletemotClef(MotClefDTO motClefToDelete)
			throws TransactionalConnectionException {
		IMotClefDAO motClefDAO = BaseConcreteFactory.getInstance()
				.createMotClefDAO(this);
		motClefDAO.deletemotClef(motClefToDelete);
	}

}
