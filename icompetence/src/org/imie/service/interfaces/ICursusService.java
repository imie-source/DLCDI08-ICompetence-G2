package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.CursusDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface ICursusService extends ITransactional {

	List<CursusDTO> findAll() throws TransactionalConnectionException;

	CursusDTO findbyid(Integer cursusid) throws TransactionalConnectionException;

	CursusDTO insertCursus(CursusDTO cursusToInsert)
			throws TransactionalConnectionException;

	CursusDTO updateCursus(CursusDTO cursusToUpdate)
			throws TransactionalConnectionException;

	

}