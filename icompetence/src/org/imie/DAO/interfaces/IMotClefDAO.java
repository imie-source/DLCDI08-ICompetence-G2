package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.MotClefDTO;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface IMotClefDAO {

	public abstract List<MotClefDTO> findAll() throws TransactionalConnectionException;

	public abstract MotClefDTO findById(Integer motClefid) throws TransactionalConnectionException;
	
	public abstract MotClefDTO insertmotClef(MotClefDTO motClefToInsert) throws TransactionalConnectionException;
	
	public abstract MotClefDTO updatemotClef(MotClefDTO motClefToUpdate) throws TransactionalConnectionException;
	
	public abstract void deletemotClef(MotClefDTO motClefToDelete) throws TransactionalConnectionException;
	
	
}
