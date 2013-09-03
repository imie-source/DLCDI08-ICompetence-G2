package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.AdresseDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;



public interface IAdresseDAO extends ITransactional {
	
	
	
	public abstract List<AdresseDTO> getAdresseByUser(UserDTO userDTO) throws TransactionalConnectionException;

	 public abstract AdresseDTO UpdateAdresse(AdresseDTO adresseToUpdate) throws TransactionalConnectionException;

	public abstract AdresseDTO createAdresse(AdresseDTO adresseToCreate) throws TransactionalConnectionException;
}
