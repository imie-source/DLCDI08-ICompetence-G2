package org.imie.DAO.interfaces;


import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * interface de DAO de la table Groupe_de_travail
 * @author imie
 *
 */
public interface IGroupWorkDAO extends ITransactional {
	/**
	 * update chef de projet
	 * @param userDTO
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract void updateUserGroupWork (UserDTO userToDelete) throws TransactionalConnectionException;
	
	
	
}
