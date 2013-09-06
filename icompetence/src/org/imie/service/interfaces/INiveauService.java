package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.NiveauDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface INiveauService extends ITransactional {
	
	List<NiveauDTO> findAll() throws TransactionalConnectionException;

	NiveauDTO findbyid(Integer niveauid) throws TransactionalConnectionException;
	
	NiveauDTO insertNiveau(NiveauDTO niveauToInsert) throws TransactionalConnectionException;
	
	NiveauDTO updateNiveau(NiveauDTO niveauToUpdate) throws TransactionalConnectionException;
	
	void deleteNiveau(NiveauDTO niveauToDelete) throws TransactionalConnectionException;

}
