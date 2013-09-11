package org.imie.DAO;

import java.util.List;

import org.imie.DAO.interfaces.IMotClefDAO;
import org.imie.DTO.MotClefDTO;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class MotClefDAO extends ATransactional implements IMotClefDAO{

	@Override
	public List<MotClefDTO> findAll() throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MotClefDTO findById(Integer motClefid)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MotClefDTO insertmotClef(MotClefDTO motClefToInsert)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MotClefDTO updatemotClef(MotClefDTO motClefToUpdate)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletemotClef(MotClefDTO motClefToDelete)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
