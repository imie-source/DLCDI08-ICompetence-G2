package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.NiveauDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface INiveauService extends ITransactional {
	
	List<NiveauDTO> findAll() throws TransactionalConnectionException;

	NiveauDTO findbyid(Integer niveauid) throws TransactionalConnectionException;

}
