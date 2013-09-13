package org.imie.DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.MotClefDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface IMotClefDAO extends ITransactional{

	public abstract List<MotClefDTO> findAll() throws TransactionalConnectionException;

	public abstract MotClefDTO findById(Integer motClefid) throws TransactionalConnectionException;
	
	public abstract MotClefDTO insertmotClef(MotClefDTO motClefToInsert) throws TransactionalConnectionException;
	
	public abstract MotClefDTO updatemotClef(MotClefDTO motClefToUpdate) throws TransactionalConnectionException;
	
	public abstract void deletemotClef(MotClefDTO motClefToDelete) throws TransactionalConnectionException;
	
	public abstract List<CompetenceDTO> compentenceParMotClef (String motClef) throws TransactionalConnectionException;

	
}
