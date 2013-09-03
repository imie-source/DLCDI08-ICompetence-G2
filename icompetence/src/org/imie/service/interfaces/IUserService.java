package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface IUserService extends ITransactional {

	/**
	 * @return liste des users avec alimentation des compétences sans filtre
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException 
	 */
	public abstract List<UserDTO> getUsers() throws TransactionalConnectionException;

	public abstract UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException;

	public abstract UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException;

	public abstract void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException;
	
	
}