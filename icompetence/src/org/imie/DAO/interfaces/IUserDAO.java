package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * interface de DAO de gestion des utlisateurs
 * 
 * @author imie
 * 
 */
public interface IUserDAO extends ITransactional {

	/**
	 * rechercher tous les utlisateurs
	 * 
	 * @return liste des users avec alimentation des compétences sans filtre
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract List<UserDTO> getUsers() throws TransactionalConnectionException;

	/**
	 * insérer un utilisateur
	 * 
	 * @param userToInsert
	 * @return l'utilisateur indéré
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException;

	/**
	 * mettre à jour un utlisateur
	 * 
	 * @param userToUpdate
	 * @return l'utilisateur mis à jour
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException;

	/**
	 * supprimer un utilisateur
	 * 
	 * @param userToDelete
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException;

	/**
	 * @param UserDTO (un volontaire à partir i.e : le login, le nom. Utilise une requête dynamique )
	 * @return UserDTO 
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException 
	 */	
	public abstract UserDTO getUser(UserDTO userToFind) throws TransactionalConnectionException;

	void attachementCompetence(int userid, int competenceid, int niveauid)
			throws TransactionalConnectionException;

	void modifattachementCompetence(int userid, int competenceid, int niveauid)
			throws TransactionalConnectionException;
}