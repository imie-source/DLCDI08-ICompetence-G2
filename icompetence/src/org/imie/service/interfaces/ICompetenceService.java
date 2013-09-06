package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface ICompetenceService extends ITransactional {
	
	List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO) throws TransactionalConnectionException;
	
	List<CompetenceDTO> findAll() throws TransactionalConnectionException;
	
	CompetenceDTO findById(Integer competenceid) throws TransactionalConnectionException;
	
	CompetenceDTO insertCompetence(CompetenceDTO competenceToInsert) throws TransactionalConnectionException;
	
	CompetenceDTO updateCompetence(CompetenceDTO competenceToUpdate) throws TransactionalConnectionException;
	
	void deleteCompetence(CompetenceDTO competenceToDelete)	throws TransactionalConnectionException;
	
}
