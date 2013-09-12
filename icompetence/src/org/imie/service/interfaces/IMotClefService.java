package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.MotClefDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface IMotClefService extends ITransactional {
	
	List<MotClefDTO> findAll() throws TransactionalConnectionException;

	MotClefDTO findById(Integer motClefid)
			throws TransactionalConnectionException;

	MotClefDTO insertmotClef(MotClefDTO motClefToInsert)
			throws TransactionalConnectionException;

	MotClefDTO updatemotClef(MotClefDTO motClefToUpdate)
			throws TransactionalConnectionException;

	void deletemotClef(MotClefDTO motClefToDelete)
			throws TransactionalConnectionException;
}
